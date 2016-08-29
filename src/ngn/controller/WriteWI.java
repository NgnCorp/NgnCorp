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

    public static void WriteWI(String[] Arr) {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(ReadWI.PATH, false), "windows-1251"))) {
            for (String item : Arr) {
                bw.write(item + ",");
            }
            bw.write("\r\n");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void WriteLDB(String[] Arr) {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(PATHLDB, true), "windows-1251"))) {
            for (String item : Arr) {
                bw.write(item + " ");
            }
            bw.write("|\r\n");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void WriteInNewFile(String[] custCard) {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\in.txt", true), "windows-1251"))) {
            for (String item : custCard) {
                bw.write(item + " ");
            }
            bw.write("|\n");
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
