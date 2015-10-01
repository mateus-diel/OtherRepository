package Cliente.Server;



import java.awt.event.KeyEvent;
import java.io.*;
import java.net.*;

/**
 *
 * @author Mateus
 */
public class ClienteForm extends javax.swing.JFrame {

    public static Socket socket;
    public static BufferedReader in;
    public static PrintWriter out;
    public static BufferedReader inReader;
    public static String mensagemEnviar;
    
    String numeroPorta1;
    ServerSocket serverSocket1;
    Socket clientSocket1;
    PrintWriter out1;
    BufferedReader in1;
    String comando1;

    /**
     * Creates new form NewJFrame
     */
    public ClienteForm() throws IOException {
        initComponents();
        this.setVisible(true);
        msg();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jbEnviar = new javax.swing.JButton();
        jtIP = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TECNIROSA - CHAT");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jbEnviar.setText("Enviar");
        jbEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbEnviarActionPerformed(evt);
            }
        });

        jLabel1.setText("IP:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(34, 34, 34))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtIP, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(503, 503, 503)
                                .addComponent(jbEnviar))
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 755, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(49, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jtIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jbEnviar))
                .addGap(20, 20, 20))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void msg() throws IOException {
        /* Parametros */
        numeroPorta1 = "7789";
        serverSocket1 = new ServerSocket(new Integer(numeroPorta1).intValue());
        while (true) {
            /* Espera por um cliente */
            clientSocket1 = serverSocket1.accept();
            /* Preparacao dos fluxos de entrada e saida */
            in = new BufferedReader(new InputStreamReader(clientSocket1.getInputStream()));
            /* Recuperacao dos comandos */
            while ((comando1 = in.readLine()) != null) {
                jTextArea1.append("Stranger: " + comando1 + "\n");
            }
        }
    }
    
    private void jbEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbEnviarActionPerformed
        try {
            /* Pegar parametros */
            String nomeServidor = jtIP.getText();
            String numeroPorta = "6789";
            /* Inicializacao de socket TCP */
            socket = new Socket(nomeServidor, new Integer(numeroPorta).intValue());
            /* Inicializacao dos fluxos de entrada e saida */
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            String str = jTextField1.getText();
            InputStream is = new ByteArrayInputStream(str.getBytes());
            // read it with BufferedReader
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            /* Abertura da entrada padrao */
            inReader = new BufferedReader(br);
            while ((mensagemEnviar = inReader.readLine()) != null) {
                /* Envio da mensagem */
                out.println(mensagemEnviar);
                jTextArea1.append("EU: " + mensagemEnviar + "\n");
                jTextField1.setText("");
            }
            /* Finaliza tudo */
            out.close();
            in.close();
            socket.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jbEnviarActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            jbEnviarActionPerformed(null);
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClienteForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClienteForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClienteForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClienteForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        ClienteForm f = new ClienteForm();
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton jbEnviar;
    private javax.swing.JTextField jtIP;
    // End of variables declaration//GEN-END:variables
}