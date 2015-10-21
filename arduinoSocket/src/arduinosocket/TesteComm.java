package arduinosocket;

/**
 *
 * @author Mateus
 */
public class TesteComm {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
       // ControlePorta conn = new ControlePorta("COM11", 9600);
       int num = 0;
       enviaDados envia = new enviaDados("192.168.1.109", "80");
       for (int i = 0; i < 5000 ; i++){
           envia.enviaDadosArduino("CoMaNd "+String.valueOf(num));
           num++;
           Thread.sleep(1000);
           
       }
        
    }
    
}
