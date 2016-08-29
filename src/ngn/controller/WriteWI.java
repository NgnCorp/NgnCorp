package ngn.controller;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 *
 * @author Офис
 */
public class WriteWI {

    public static final String PATHLDB = "C:\\NgnUpdater\\LDB.txt";

    public static void Write(String[] Arr, String path, Boolean Rewrite) {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path, Rewrite), "windows-1251"))) {
            for (String item : Arr) {
                bw.write(item + "=>");
            }
            bw.write("|\r\n");
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
