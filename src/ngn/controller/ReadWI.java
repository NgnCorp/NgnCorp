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
    public static File outputFile = new File(Paths.CACHELDBPATH);

    public static void ReadWI() {

        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(Paths.TRANSACTIONPATH), "windows-1251")) {
            data = isr.read();
            if (data > 0) {
                Content = new StringBuilder(data);
                while (data != -1) {
                    Content.append((char) data);
                    data = isr.read();
                }
                Transactions = String.valueOf(Content).split("\\|");
                if (BackendTimers.InternetCheck && DB.SendTransactionsToDB(Transactions)) {
                    WriteWI.FillingDataToZero();
                    System.out.println("Transactions were send");
                } else {
                    System.out.println("Can't connect to server DB");
                }
            } else {
                System.out.println("No Data in FillingData.txt");
            }
        } catch (IOException ex) {
            BSLoadingText.setText(Text.cannotreadTR);
            System.out.println(ex);
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
        }
        CustomerInfo = String.valueOf(LDB).split("\\|");
    }

    public static boolean FindCardName(String cardName) {
        String CardCode = cardName.toUpperCase();
        CreateLocalDB();
        String[] CCS;
        for (String custCard : CustomerInfo) {
            CCS = custCard.split("=>");
            if (CCS[0].toUpperCase().contains(CardCode)) {
                PersonalInfo = CCS;
                return true;
            }
        }
        return false;
    }

    public static void ReWrite(String cardName, String[] UpdateVariables) {
        String CardCode = cardName.toUpperCase();
        String[] CCS;
        String[] UpdateData = new String[]{
            cardName,
            String.valueOf(Variables.customerId),
            Variables.pin,
            Variables.name,
            UpdateVariables[0],
            Variables.code,
            String.valueOf(Variables.customerPrice),
            String.valueOf(Variables.uahBalance),
            Variables.purse,
            String.valueOf(Variables.limitDay),
            String.valueOf(Variables.limitLitrs),
            UpdateVariables[1],
            String.valueOf(Variables.litrPlace),
            String.valueOf(Variables.couponId),
            String.valueOf(Variables.credit),
            UpdateVariables[2]
        };
        //System.out.println(Arrays.toString(UpdateData));
        for (String custCard : CustomerInfo) {
            CCS = custCard.split("=>");
            if (CCS[0].toUpperCase().contains(CardCode)) {
                WriteWI.Write(UpdateData, Paths.CACHELDBPATH, true);
            } else {
                WriteWI.Write(CCS, Paths.CACHELDBPATH, true);
            }
        }
        sourceFile.delete();
        outputFile.renameTo(sourceFile);
        try {
            outputFile.createNewFile();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
