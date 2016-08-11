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
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("/Data/FillingData.txt"))) {
            for (String item : Arr) {
                bw.write(item+",");
            }
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
}
