package com.acme.logic;

import br.com.acme.model.AcademicLibrary;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ALManager {

    /**
     * Declaração de library(AcademicLibrary)
     */
    private static AcademicLibrary library;

    static {
        newLibrary();
    }

    /**
     * @return library.
     */
    public static AcademicLibrary getInstance() {
        return library;
    }

    /**
     * Cria um library passando ao construtor tres strings 'Name, Description,
     * publication Date.
     */
    public static void newLibrary() {
        library = new AcademicLibrary("RZF Pub", "Scientific publications", "21-01-2007");
    }

    /**
     * Implemente o código deste método de forma que seja possível ler um
     * arquivo no disco rígido onde tenha sido armazenado o objeto 'library'.
     *
     * @param file Recebe caminho do aqruivo.
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     */
    public static void loadLibrary(File file) throws FileNotFoundException, IOException, ClassNotFoundException {
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            library = (AcademicLibrary) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException ex) {
            LogErros.gravarArquivo(ex);
        }
    }

    /**
     * Implemente o código deste método de forma que o objeto 'library' seja
     * persistido em um arquivo no disco rígido.
     *
     * @param file Recebe caminho do arquivo.
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    public static void persistLibrary(File file) throws FileNotFoundException, IOException {
        try {
            FileOutputStream fas = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fas);
            oos.writeObject(ALManager.getInstance());
            oos.close();
            fas.close();
        } catch (FileNotFoundException e) {
            LogErros.gravarArquivo(e);
        } catch (IOException e) {
            LogErros.gravarArquivo(e);
        }

    }

}
