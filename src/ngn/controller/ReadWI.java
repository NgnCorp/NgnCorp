package ngn.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author Офис
 */
public class ReadWI {

    public static StringBuilder allText;
    public static int data;
    public static String[] mas;
    public static final String PATH = "C:\\NgnUpdater\\FillingData.txt";

    public static void ReadWI() {

        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(PATH), "windows-1251")) {
            // чтение посимвольно
            data = isr.read();
            allText = new StringBuilder(data);
            while (data != -1) {
                allText.append((char) data);
                data = isr.read();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        mas = String.valueOf(allText).split(",");
    }
}
