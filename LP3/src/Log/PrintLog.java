package Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Mateus
 */
public class PrintLog {

    /**
     * Declaração dos atributos.
     */
    private static File erros;
    public static boolean isActive;

    /**
     * Este método possui a variavel booleana que se coincidir com true então o 
     * log será gravado. É estatico para poder ser acessado de outras classes.
     * @param isActive boolean.
     */
    public static void setIsActive(boolean isActive) {
        PrintLog.isActive = isActive;
    }

    /**
     * Este metodo tem como objetivo pegar o erro e gravar em um determinado
     * local do disco em um arquivo.
     * @param e recebe erro a ser gravado.
     */
    public static void gravarArquivo(Exception e) {
        if (isActive) {
            try {
                Date data = new Date();
                //string caminho deve conter o caminho onde o arquivo será salvo
                String caminho = ".";
                erros = new File(caminho);
                erros.mkdir();

                File destino = new File(erros, "alcs.log");

                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                e.printStackTrace(pw);
                sw.toString();

                // este true deixa adicionar novas palavras e linhas
                FileWriter fw = new FileWriter(destino, true);
                BufferedWriter bw = new BufferedWriter(fw);

                bw.write("[" + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(data) + "]");
                bw.newLine();
                bw.write("    " + sw);
                bw.newLine();
                bw.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static void gravaLog(String e){
         if (isActive) {
            try {
                Date data = new Date();
                //string caminho deve conter o caminho onde o arquivo será salvo
                String caminho = ".";
                erros = new File(caminho);
                erros.mkdir();

                File destino = new File(erros, "alcs.log");

                // este true deixa adicionar novas palavras e linhas
                FileWriter fw = new FileWriter(destino, true);
                BufferedWriter bw = new BufferedWriter(fw);

                bw.write("[" + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(data) + "]");
                bw.newLine();
                bw.write("    " + e);
                bw.newLine();
                bw.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    }

