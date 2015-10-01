package Data;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Mateus
 */
public class DatabaseTest{

    static final String DatabaseURL = "jdbc:mysql://192.168.10.130:3306/estoque";

    public static void main(String[] args) throws SQLException {

        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(DatabaseURL, "mdk", "@grimefocus1");
            System.out.println(connection.toString());
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM estoque.cliente");
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numbersOfColums = metaData.getColumnCount();
            System.out.println("Author Table of Books Database : \n");

            for (int i = 1; i <= numbersOfColums; i++) {
                System.out.print("\t" + metaData.getColumnName(i) + "      ");
                System.out.print("");
            }
            System.out.println("");
            int z = 0;
            while (resultSet.next()) {

                String teste = "";
                for (int i = 1; i <= numbersOfColums; i++) {
                    System.out.print("\t" + resultSet.getObject(i) + "       ");
                    System.out.print("");
                    z++;
                    if (z == 1) {
                        teste += ("     "+resultSet.getObject(i).toString() + "      ");
                    }
                    if (z == 2) {
                        teste += (resultSet.getObject(i).toString() + "      ");
                    }
                    if (z == 3) {
                        teste += (resultSet.getObject(i).toString());
                        System.out.println("");
                        z = 0;
                    }
                }
                JOptionPane.showMessageDialog(null, "cdcli" + "          nm cli" + "          tipocli\n"+teste);
            }
        } catch (SQLException sql) {
            sql.printStackTrace();
        } catch (Exception e) {
        } finally {
            try {
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
}
