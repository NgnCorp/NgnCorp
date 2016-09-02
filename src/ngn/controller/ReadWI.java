package ngn.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
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
        Transactions = String.valueOf(Content).split("\\|");
        if (DB.SendTransactionsToDB(Transactions)) {
            System.out.println("Send");
        } else {
            System.out.println("Oops");
        }
    }

    public static void CreateLocalDB() throws MessagingException, UnsupportedEncodingException {

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
    }

    public static boolean FindCardName(String cardName) {
        String CardCode = cardName.toUpperCase();

        for (String custCard : CustomerInfo) {
            if (custCard.split("=>")[0].toUpperCase().contains(CardCode)) {
                PersonalInfo = custCard.split("=>");
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
