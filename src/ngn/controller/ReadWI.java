package ngn.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import ngn.text.Paths;
import ngn.text.Text;
import static ngn.view.BeforeStart.BSLoadingText;

/**
 *
 * @author Офис
 */
public class ReadWI {

    public static StringBuilder allText;
    public static StringBuilder allDB;
    public static int data;
    public static int text;
    public static String[] customerInfo;
    public static String[] personalInfo;
    public static String[] mas;
    public static File sourceFile = new File(Paths.LDBPATH);
    public static File outputFile = new File(Paths.PATH2);

    public static void ReadWI() {

        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(Paths.TRANSACTIONPATH), "windows-1251")) {
            // чтение транзакций и запись их на сервер
            data = isr.read();
            if (data > 0) {
                allText = new StringBuilder(data);
                while (data != -1) {
                    allText.append((char) data);
                    data = isr.read();
                }
            }
        } catch (IOException ex) {
            BSLoadingText.setText(Text.cannotreadTR);
            System.out.println(ex);
        }
        mas = String.valueOf(allText).split("=>");
        System.out.println(Arrays.toString(mas));
    }

    public static void CreateLocalDB() {

        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(Paths.LDBPATH), "windows-1251")) {
            // чтение посимвольно
            text = isr.read();
            if (text > 0) {
                allDB = new StringBuilder(text);
                while (text != -1) {
                    allDB.append((char) text);
                    text = isr.read();
                }
            }
        } catch (IOException ex) {
            BSLoadingText.setText(Text.cannotreadDB);
            System.out.println(ex);
        }
        customerInfo = String.valueOf(allDB).split("\\|");
    }

    public static boolean FindCardName(String cardName) {
        String CardCode = cardName.toUpperCase();

        for (String custCard : customerInfo) {
            if (custCard.split("=>")[0].toUpperCase().contains(CardCode)) {
                personalInfo = custCard.split("=>");
                return true;
            }
            //else {
            //    WriteWI.WriteInNewFile(custCard.split(" "));
            //}
        }
        /*
        sourceFile.delete();
        outputFile.renameTo(sourceFile);
        try {
            outputFile.createNewFile();
        } catch (IOException ex) {
            System.out.println(ex);
        }
         */
        return false;
    }
}
