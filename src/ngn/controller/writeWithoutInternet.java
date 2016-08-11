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
<<<<<<< HEAD
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("/FillingData.txt"))) {
=======
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("D:/FillingData.txt",true))) {
>>>>>>> origin/master
            for (String item : Arr) {
                bw.write(item + ",");
            }
            bw.write("\r\n");
        } catch (IOException ex) {
<<<<<<< HEAD
            
        System.out.println(ex.getMessage());
=======
            System.out.println(ex.getMessage());
>>>>>>> origin/master
        }
    }
}
