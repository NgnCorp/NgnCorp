package ngn.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Офис
 */
public class ReadWI {

    public static StringBuilder allText;
    public static String[] mas;
    public static final String PATH = "C:\\NgnUpdater\\FillingData.txt";

    public static void ReadWI() {

        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(PATH), "windows-1251")) {
            // чтение посимвольно
            allText = new StringBuilder();
            char[] buff = new char[1];
            int c;
            while ((c = isr.read()) != -1) {
                allText.append(buff, 0, c);
            }
            System.out.println(allText);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        //mas = String.valueOf(allText).split(",");
    }
}
