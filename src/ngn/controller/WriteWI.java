package ngn.controller;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import mail.SendMail;
import ngn.model.DB;
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
                counter++;
                if (counter < Arr.length) {
                    bw.write(item + "=>");
                } else {
                    bw.write(item);
                }
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
            SendMail.sendEmail(String.valueOf(ex), "Can't LDBToZero error! " + DB.MODULENAME);
            System.out.println(ex.getMessage());
        }
    }
}
