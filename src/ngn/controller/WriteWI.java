package ngn.controller;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import mail.SendMail;
import ngn.model.DB;
import ngn.text.Paths;

/**
 *
 * @author Офис
 */
public class WriteWI {

    public static final String PATHLDB = Paths.LDBPATH;
    public static SimpleDateFormat ft = new SimpleDateFormat("HH:mm:ss / dd.MM.yyyy");

    public static void Write(String[] Arr, String path, Boolean Rewrite) {
        int counter = 0;
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path, Rewrite), "windows-1251"))) {
            for (String item : Arr) {
                counter++;
                if (counter < Arr.length) {
                    bw.write(item + "=>");
                } else if (item.endsWith("|")) {
                    bw.write(item.substring(0, item.length() - 1));
                } else {
                    bw.write(item);
                }
            }
            
            bw.write("|");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void LDBToZero() {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(PATHLDB, false), "windows-1251"))) {
            bw.write("");
        } catch (IOException ex) {
            SendMail.sendEmail(String.valueOf(ex), "Can't LDBToZero error! " + DB.MODULENAME, false);
            System.out.println(ex);
        }
    }

    public static void FillingDataToZero() {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Paths.TRANSACTIONPATH, false), "windows-1251"))) {
            bw.write("");
        } catch (IOException ex) {
            SendMail.sendEmail(String.valueOf(ex), "Can't FillingDataToZero error! " + DB.MODULENAME, false);
            System.out.println(ex);
        }
    }

    public static void CacheDataToZero() {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Paths.CACHEDATAPATH, false), "windows-1251"))) {
            bw.write("");
        } catch (IOException ex) {
            SendMail.sendEmail(String.valueOf(ex), "Can't FillingDataToZero error! " + DB.MODULENAME, false);
            System.out.println(ex);
        }
    }

    public static void CounterWriter(Double litriDouble) {
        double LitrsFromCounter = Double.parseDouble(ReadWI.CounterReader());
        try (final FileWriter writer = new FileWriter(Paths.COUNTERPATH, false)) {
            double LitrsCounter = LitrsFromCounter + litriDouble;
            final String s = String.format(Locale.ENGLISH, "%.2f", LitrsCounter);
            writer.write(s + " |");
            writer.write(System.lineSeparator());
            String date = String.valueOf(ft.format(new Date()));
            writer.write(date);
        } catch (IOException e) {
            SendMail.sendEmail(String.valueOf(e.getMessage()), "Can't Write to counter! " + DB.MODULENAME, false);
            System.out.println(e);
        }
    }
}
