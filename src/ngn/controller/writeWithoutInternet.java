package ngn.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Офис
 */
public class writeWithoutInternet {

    public static void writeWithoutInternet(String[] Arr) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("/FillingData.txt"))) {
            for (String item : Arr) {
                bw.write(item + ",");
            }
            bw.write("\r\n");
        } catch (IOException ex) {
            
        System.out.println(ex.getMessage());
        }
    }
}
