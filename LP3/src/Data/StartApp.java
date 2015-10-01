package Data;

import Forms.LoginForm;
import java.sql.SQLException;

/**
 * Classe com metodo main, inicializa a aplicação
 * @author Mateus
 */



public class StartApp {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws SQLException {
        LoginForm l = new LoginForm();
        l.setVisible(true);
        //seta a visibilidade
    }
}
