package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.AbstractTableModel;

/**
 * Esta classe possui operações referentes ao banco de dados
 * @author Mateus
 */
public class ModelTableUpgradeable extends AbstractTableModel {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private ResultSetMetaData metaData;
    private int numberOfRows;
    private boolean connectedToDatabase = false;

    /**
     * Cria uma conexão passando a url, usuario e senha
     * @param url
     * @param user
     * @param password
     * @throws SQLException
     */
    public ModelTableUpgradeable(String url, String user, String password) throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        connectedToDatabase = true;
        resultSet = statement.executeQuery("select * from cliente");
        metaData = resultSet.getMetaData();
    }

    @Override
    public Class getColumnClass(int column) throws IllegalStateException {
        if (!connectedToDatabase) {
            System.out.println("Not Connected to Database");
        } else {
            try {
                String className = metaData.getColumnClassName(column + 1);
                return Class.forName(className);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return Object.class;
    }

    @Override
    public int getColumnCount() throws IllegalStateException {
        if (!connectedToDatabase) {
            System.out.println("Not Connected to Database");
        } else {
            try {
                return metaData.getColumnCount();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public String getColumnName(int column) throws IllegalStateException {
        if (!connectedToDatabase) {
            System.out.println("Not Connected to Database");
        } else {
            try {
                return metaData.getColumnName(column + 1);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return "";
    }

    @Override
    public int getRowCount() throws IllegalStateException {
        return numberOfRows;
    }

    @Override
    public Object getValueAt(int row, int column) throws IllegalStateException {
        if (!connectedToDatabase) {
            System.out.println("Not Connected to Database");
        } else {
            try {
                resultSet.absolute(row + 1);
                return resultSet.getObject(column + 1);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return "";
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return true;
    }

    @Override
    public void setValueAt(Object value, int row, int col) {

        try {
            resultSet.absolute(row + 1);
            resultSet.updateObject(col + 1, value);
            resultSet.updateRow();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        fireTableCellUpdated(row, col);

    }

    /**
     * Faz uma consulta no banco de dados através de uma string que contém a
     * linha de código
     *
     * @param query
     * @throws IllegalStateException
     * @throws SQLException
     */
    public void setQuery(String query) throws IllegalStateException, SQLException {
        resultSet = statement.executeQuery(query);
        metaData = resultSet.getMetaData();
        resultSet.last();
        numberOfRows = resultSet.getRow();
        fireTableStructureChanged();
    }

    /**
     * Este método fecha os objetos
     */
    public void disconnectFromDatabase() {
        if (connectedToDatabase) {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                connectedToDatabase = false;
            }
        }
    }
}
