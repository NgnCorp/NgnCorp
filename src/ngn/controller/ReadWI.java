package ngn.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
    public static final String PATH = "C:\\NgnUpdater\\FillingData.txt";
    public static final String PATH1 = "D:\\LDB.txt";
    public static final String PATH2 = "D:\\LDB1.txt";
    public static File sourceFile = new File("D:\\out.txt");
    public static File outputFile = new File("D:\\in.txt");

    public static void ReadWI() {

        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(PATH), "windows-1251")) {
            // чтение посимвольно
            data = isr.read();
            if (data > 0) {
                allText = new StringBuilder(data);
                while (data != -1) {
                    allText.append((char) data);
                    data = isr.read();
                }
            }
        } catch (IOException ex) {
            BSLoadingText.setText("Error!");
        }
        mas = String.valueOf(allText).split(",");
    }

    public static void CreateLocalDB() {

        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(PATH1), "windows-1251")) {
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
            //BSLoadingText.setText("Error!");
        }
        customerInfo = String.valueOf(allDB).split("\\|");
    }

    public static void FindCardName(String cardName) throws IOException {

        for (String custCard : customerInfo) {
            //System.out.println(custCard.split(" ")[0]);
            if (custCard.split(" ")[0].contains(cardName)) {
                personalInfo = custCard.split(" ");
                System.out.println(custCard.split(" ")[0]);
            } else {
                WriteWI.WriteInNewFile(custCard.split(" "));
            }
            //System.out.println(cardName);
        }
        sourceFile.delete();
        outputFile.renameTo(sourceFile);
        outputFile.createNewFile();
    }
}
