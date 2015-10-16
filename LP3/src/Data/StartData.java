package Data;

import Forms.queryForm;
import Log.PrintLog;
import java.awt.Dimension;
import static java.lang.Thread.sleep;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import org.netbeans.lib.awtextra.AbsoluteConstraints;
import org.netbeans.lib.awtextra.AbsoluteLayout;

/**
 * Classe que simula uma tela de carregamento
 * @author Mateus
 */
public class StartData extends JWindow {

    AbsoluteLayout absoluto;
    AbsoluteConstraints absimage, absbarra;
    ImageIcon image;
    JLabel jlabel;
    JProgressBar barra;

    /**
     * Usa-se este metodo para setar a visibilidade do form
     */
    public void setVisi() {
        this.setVisible(false);
    }

    /**
     * Este método recebe o login e senha do form de login, e passa para o form
     * queryForm, simula também um suposto carregamento
     *
     * @param login
     * @param pw
     */
    public StartData(final String user, final String login, final String pw) {
        absoluto = new AbsoluteLayout();
        absimage = new AbsoluteConstraints(0, 0);
        absbarra = new AbsoluteConstraints(0, 115);
        jlabel = new JLabel();
        image = new ImageIcon(this.getClass().getResource("icon.png"));
        jlabel.setIcon(image);
        barra = new JProgressBar();
        barra.setPreferredSize(new Dimension(130, 10));
        this.getContentPane().setLayout(absoluto);
        this.getContentPane().add(jlabel, absimage);
        this.getContentPane().add(barra, absbarra);
        new Thread() {
            @Override
            public void run() {
                int i = 0;
                while (i < 101) {
                    barra.setValue(i);
                    i++;
                    try {
                        sleep(30);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                        PrintLog.gravarArquivo(ex);
                    }
                }
                queryForm start;
                try {
                    start = new queryForm(user, login, pw);
                    start.setVisible(true);
                    setVisi();
                } catch (Exception ex) {
                    setVisi();
                    ex.printStackTrace();
                    PrintLog.gravarArquivo(ex);
                }
            }
        }.start();
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

}
