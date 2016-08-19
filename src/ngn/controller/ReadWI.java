package ngn.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import static ngn.text.Text.h1CheckUpdate;
import static ngn.view.BeforeStart.LoadingText;

/**
 *
 * @author Офис
 */
public class ReadWI {

    public static StringBuilder allText;
    public static String[] mas;
    public static final String path= "D:/FillingData.txt";
    
    public static void ReadWI() {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            // чтение посимвольно
            allText = new StringBuilder();
            char[] buff = new char[1];
            int c;
            while ((c = br.read(buff)) != -1) {
                allText.append(buff, 0, c);
            }
            System.out.println(allText);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        mas = String.valueOf(allText).split(",");
    }
}
