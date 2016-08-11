package ngn.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Array;

/**
 *
 * @author Офис
 */
public class writeWithoutInternet {

    public static void writeWithoutInternet(String[] Arr) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("D:/FillingData.txt",true))) {
            for (String item : Arr) {
                bw.write(item + ",");
            }
            bw.write("\r\n");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
