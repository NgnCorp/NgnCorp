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

    public static void WriteWI(String[] Arr) {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ReadWI.PATH, true), "windows-1251"))) {
            for (String item : Arr) {
                bw.write(item + ",");
            }
            bw.write("\r\n");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void WriteLDB(String[] Arr) {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ReadWI.PATH, false), "windows-1251"))) {
            for (String item : Arr) {
                bw.write(item + ",");
            }
            bw.write("\r\n");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
