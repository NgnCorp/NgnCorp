package ngn.controller;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import ngn.text.Paths;

/**
 *
 * @author Офис
 */
public class WriteWI {

    public static final String PATHLDB = Paths.LDBPATH;

    public static void Write(String[] Arr, String path, Boolean Rewrite) {
        int counter = 0;
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path, Rewrite), "windows-1251"))) {
            for (String item : Arr) {
                if (counter < Arr.length) {
                    bw.write(item + "=>");
                    counter++;
                } else {
                    bw.write(item + "|\r\n");
                }
            System.out.println(counter);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void LDBToZero() {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(PATHLDB, false), "windows-1251"))) {
            bw.write("");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
