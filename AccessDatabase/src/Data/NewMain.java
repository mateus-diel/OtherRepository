package Data;

import Forms.queryForm;
import java.sql.SQLException;

/**
 *
 * @author Mateus
 */


public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        
        queryForm q = new queryForm();
        q.setVisible(true);
    }
    
}
