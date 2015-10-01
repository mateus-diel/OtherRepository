package Data;

import Forms.frame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.AbstractTableModel;

public class ResultSetTableModel extends AbstractTableModel {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private ResultSetMetaData metaData;
    private int numberOfRows;
    private boolean connectedToDatabase = false;
    private PreparedStatement prepare;
    private PreparedStatement prepCliName;
    private PreparedStatement prepCliCod;
    private PreparedStatement prepProName;
    private PreparedStatement prepProCod;
    private PreparedStatement prepVenCod;
    private PreparedStatement prepVenName;

    public ResultSetTableModel(String url, String user, String password, String query) throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        connectedToDatabase = true;
        setQuery(query);
        prStatement();
    }

    public ResultSetTableModel() {

    }

    @Override
    public Class getColumnClass(int column) throws IllegalStateException {
        if (!connectedToDatabase) {
            System.out.println("Not Connected to Database");
        }
        try {
            String className = metaData.getColumnClassName(column + 1);
            return Class.forName(className);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Object.class;
    }

    @Override
    public int getColumnCount() throws IllegalStateException {
        try {
            return metaData.getColumnCount();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    @Override
    public String getColumnName(int column) throws IllegalStateException {
        try {
            return metaData.getColumnName(column + 1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    @Override
    public int getRowCount() throws IllegalStateException {
        return numberOfRows;
    }

    @Override
    public Object getValueAt(int row, int column) throws IllegalStateException {
        try {
            resultSet.absolute(row + 1);
            return resultSet.getObject(column + 1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public void prepStatement(int i, int j) throws SQLException {
        prepare = connection.prepareStatement("SELECT * FROM CLIENTE WHERE CDCLI = ? AND CDTIPOCLI = ?");
        prepare.setInt(1, i);
        prepare.setInt(2, j);
        resultSet = prepare.executeQuery();
        metaData = resultSet.getMetaData();
        resultSet.last();
        numberOfRows = resultSet.getRow();
        fireTableStructureChanged();
    }

    public void searchCliName(String n) throws SQLException {
        String name = ("%" + n + "%");
        prepCliName.setString(1, name);
        resultSet = prepCliName.executeQuery();
        metaData = resultSet.getMetaData();
        resultSet.last();
        numberOfRows = resultSet.getRow();
        fireTableStructureChanged();
    }

    public void searchCliCod(String cod) throws SQLException {
        int co = Integer.parseInt(cod);
        prepCliCod.setInt(1, co);
        resultSet = prepCliCod.executeQuery();
        metaData = resultSet.getMetaData();
        resultSet.last();
        numberOfRows = resultSet.getRow();
        fireTableStructureChanged();
    }

    public void searchProName(String na) throws SQLException {
        String name = ("%" + na + "%");
        prepProName.setString(1, name);
        resultSet = prepProName.executeQuery();
        metaData = resultSet.getMetaData();
        resultSet.last();
        numberOfRows = resultSet.getRow();
        fireTableStructureChanged();
    }

    public void searchProCod(String cod) throws SQLException {
        int codigo = Integer.parseInt(cod);
        prepProCod.setInt(1, codigo);
        resultSet = prepProCod.executeQuery();
        metaData = resultSet.getMetaData();
        resultSet.last();
        numberOfRows = resultSet.getRow();
        fireTableStructureChanged();
    }

    public void searchVendedorName(String name) throws SQLException {
        String nameVen = ("%" + name + "%");
        prepVenName.setString(1, nameVen);
        resultSet = prepVenName.executeQuery();
        metaData = resultSet.getMetaData();
        resultSet.last();
        numberOfRows = resultSet.getRow();
        fireTableStructureChanged();
    }

    public void searchVendedorCod(String cod) throws SQLException {
        int codigo = Integer.parseInt(cod);
        prepVenCod.setInt(1, codigo);
        resultSet = prepVenCod.executeQuery();
        metaData = resultSet.getMetaData();
        resultSet.last();
        numberOfRows = resultSet.getRow();
        fireTableStructureChanged();
    }

    public void prStatement() throws SQLException {
        prepCliCod = connection.prepareStatement("SELECT * FROM CLIENTE WHERE CPF = ?");
        prepCliName = connection.prepareStatement("SELECT NOME,CPF,TELEFONE FROM CLIENTE WHERE NOME LIKE ?");
        prepProName = connection.prepareStatement("SELECT * FROM PRODUTO WHERE NOME LIKE ?");
        prepProCod = connection.prepareStatement("SELECT * FROM PRODUTO WHERE CODP = ?");
        prepVenCod = connection.prepareStatement("SELECT CODV,NOME,TELEFONE FROM VENDEDOR WHERE CODV = ?");
        prepVenName = connection.prepareStatement("SELECT CODV,NOME,TELEFONE FROM VENDEDOR WHERE NOME LIKE ?");
    }

    public void setQuery(String query) throws IllegalStateException, SQLException {
        resultSet = statement.executeQuery(query);
        metaData = resultSet.getMetaData();
        resultSet.last();
        numberOfRows = resultSet.getRow();

        fireTableStructureChanged();
    }

    public Connection getConnection() {
        return this.connection;
    }

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
