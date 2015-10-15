package Teste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mateus
 */
public class ConnectClass extends AbstractTableModel {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private ResultSetMetaData metaData;
    private int numberOfRows;
    private boolean connectedToDatabase;
    private PreparedStatement deleteDay;
    private PreparedStatement deleteAllDay;
    private PreparedStatement addDayWithComentary;

    public ConnectClass() throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost/calendarofpresence", "root", "@grimefocus1");
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        connectedToDatabase = true;
        loadPrepStates();
    }

    public ConnectClass(String query) throws SQLException {
        connection = DriverManager.getConnection("jdbc:mysql://localhost/calendarofpresence", "root", "@grimefocus1");
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        connectedToDatabase = true;
        setQuery(query);
        loadPrepStates();
    }

    public void setQuery(String query) throws IllegalStateException, SQLException {
        resultSet = statement.executeQuery(query);
        metaData = resultSet.getMetaData();
        resultSet.last();
        numberOfRows = resultSet.getRow();
        fireTableStructureChanged();
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

    public String numberOfDays() {
        try {
            resultSet = statement.executeQuery("select * from curseddays");
            resultSet.last();
            return Integer.toString(resultSet.getRow());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public void loadPrepStates() {
        try {
            deleteDay = connection.prepareStatement("delete from curseddays where days = ?");
            deleteAllDay = connection.prepareStatement("delete from curseddays where days = ?)");
            addDayWithComentary = connection.prepareStatement("insert into curseddays values (?,?)");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addDayComentary(String Day, String Comentary) {
        try {
            addDayWithComentary.setInt(1, Integer.parseInt(Day));
            addDayWithComentary.setString(2, Comentary);
            addDayWithComentary.execute();
            JOptionPane.showMessageDialog(null, "Day Added!", "Mensagem", 1);
        } catch (SQLException ex) {
            ex.printStackTrace();
            if (ex.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(null, "Está data já existe!", "Mensagem", 2);
            } else {
                JOptionPane.showMessageDialog(null, "Ops! Algo deu errado!", "Mensagem", 2);
            }
        }
    }

    public void deleteAllDays() {
        try {
            PreparedStatement s;
            String z = "truncate table curseddays";
            s = connection.prepareStatement(z);
            s.execute();
            JOptionPane.showMessageDialog(null, "Tudo foi deletado!", "Mensagem", 2);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ops! Algo deu errado!", "Mensagem", 2);
        }
    }

    public void deleteDay(String day) {
        try {
            deleteDay.setInt(1, Integer.parseInt(day));
            deleteDay.execute();
            JOptionPane.showMessageDialog(null, "Day Deleted!", "Mensagem", 2);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ops! Algo deu errado!", "Mensagem", 2);
        }
    }
}
