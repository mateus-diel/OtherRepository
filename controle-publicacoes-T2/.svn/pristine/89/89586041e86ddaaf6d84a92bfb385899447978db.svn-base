package com.acme.gui;

import static java.lang.Integer.parseInt;

/**
 *
 * @author Mateus
 */
public class Encrypt {
    public static String[] code = new String[10];
    public static int q;
    public static String[] uncom;

    public Encrypt() {
    }

    /**
     *
     * @param z
     * @return
     */
    public static String cripto(String zen) {
        code[0] = ":";
        code[1] = "@";
        code[2] = "!";
        code[3] = "%";
        code[4] = "#";
        code[5] = "+";
        code[6] = "$";
        code[7] = "&";
        code[8] = "(";
        code[9] = ")";
        String z = zen;
        String[] array = z.split("");
String ab = "";
int i;
        for (i = 1; i < array.length; i++) {
            int zw = parseInt(array[i]);
            ab += code[zw];
        }
        return ab;
    }

    public static String descript(String he) {
     String unprotect = "";
     String ab = he;
        uncom = ab.split("");

        int i;
        int q;
        
        for (q = 1; q < uncom.length; q++) {

            for (i = 0; i < code.length; i++) {
                if (code[i].equalsIgnoreCase(uncom[q])) {
                    unprotect += (i);
                    break;
                }
            }
        }
        return unprotect;
    }
}
