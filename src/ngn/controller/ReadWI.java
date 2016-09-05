package ngn.controller;

import Preload.BackendTimers;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import mail.SendMail;
import ngn.model.DB;
import ngn.text.Paths;
import ngn.text.Text;
import static ngn.view.BeforeStart.BSLoadingText;

/**
 *
 * @author Офис
 */
public class ReadWI {

    public static StringBuilder Content;
    public static StringBuilder LDB;
    public static int data;
    public static String[] CustomerInfo;
    public static String[] PersonalInfo;
    public static String[] Transactions;
    public static File sourceFile = new File(Paths.LDBPATH);
    public static File outputFile = new File(Paths.PATH2);

    public static void ReadWI() {

        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(Paths.TRANSACTIONPATH), "windows-1251")) {
            data = isr.read();
            if (data > 0) {
                Content = new StringBuilder(data);
                while (data != -1) {
                    Content.append((char) data);
                    data = isr.read();
                }
            }
        } catch (IOException ex) {
            BSLoadingText.setText(Text.cannotreadTR);
            System.out.println(ex);
        }
        if (Content != null) {
            Transactions = String.valueOf(Content).split("\\|");
            if (BackendTimers.InternetCheck && DB.SendTransactionsToDB(Transactions)) {
                System.out.println("Transactions were send");
            } else {
                System.out.println("Can't connect to server DB");
            }
        } else {
            System.out.println("No transactions.");
        }
    }

    public static void CreateLocalDB() {

        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(Paths.LDBPATH), "windows-1251")) {
            // чтение посимвольно
            data = isr.read();
            if (data > 0) {
                LDB = new StringBuilder(data);
                while (data != -1) {
                    LDB.append((char) data);
                    data = isr.read();
                }
            }
        } catch (IOException ex) {
            BSLoadingText.setText(Text.cannotreadDB);
            SendMail.sendEmail(String.valueOf(ex), Text.cannotreadDB + " " + DB.MODULENAME);
            System.out.println(ex);
        }
        CustomerInfo = String.valueOf(LDB).split("\\|");
            System.out.println(Arrays.toString(CustomerInfo));
    }

    public static boolean FindCardName(String cardName, String whatToDo) {
        String CardCode = cardName.toUpperCase();

        for (String custCard : CustomerInfo) {
            if (custCard.split("=>")[0].toUpperCase().contains(CardCode)) {
                PersonalInfo = custCard.split("=>");
                return true;
            } else if ("Delete".equals(whatToDo)) {
                WriteWI.Write(custCard.split("=>"), Paths.PATH2, true);
            }
        }
        if ("Delete".equals(whatToDo)) {
            sourceFile.delete();
            outputFile.renameTo(sourceFile);
            try {
                outputFile.createNewFile();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
        return false;
    }
}
