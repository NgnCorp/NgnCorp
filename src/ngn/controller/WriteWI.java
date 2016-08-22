package ngn.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Офис
 */
public class WriteWI {

    public static void WriteWI(String[] Arr) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ReadWI.path, true))) {
            for (String item : Arr) {
                bw.write(item + ",");
            }
            bw.write("\r\n");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
