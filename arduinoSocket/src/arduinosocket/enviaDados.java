package arduinosocket;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Mateus
 */
public class enviaDados {

    private Socket socket1;
    public BufferedReader in1;
    public PrintWriter out1;
    public BufferedReader inReader1;
    public String mensagemEnviar1;
    private String ip;
    private String porta;

    public enviaDados(String ip, String porta) {
        this.ip = ip;
        this.porta = porta;
        this.enviaDadosArduino("-BIP*");
    }

    public void enviaDadosArduino(String comando) {
        try {
            for (int i = 0; i < 2; i++) {
                String nomeServidor1 = ip;
                String numeroPorta1 = porta;
                /* Inicializacao de socket TCP */
                socket1 = new Socket(nomeServidor1, Integer.parseInt(numeroPorta1));
                if (!socket1.isClosed()) {
                    /* Inicializacao dos fluxos de entrada e saida */
                    in1 = new BufferedReader(new InputStreamReader(socket1.getInputStream()));
                    out1 = new PrintWriter(socket1.getOutputStream(), true);
                    String str = comando; //DADOS A SEREM ENVIADOR PARA O ARDUINO
                    InputStream is = new ByteArrayInputStream(str.getBytes());
                    // read it with BufferedReader
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    /* Abertura da entrada padrao */
                    inReader1 = new BufferedReader(br);
                    while ((mensagemEnviar1 = inReader1.readLine()) != null) {
                        /* Envio da mensagem */
                        out1.println(mensagemEnviar1);
                        System.out.println(mensagemEnviar1);
                    }
                    /* Finaliza tudo */
                    out1.close();
                    in1.close();
                    socket1.close();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, ("Erro ao enviar dados!\n" + ex.getMessage()), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
