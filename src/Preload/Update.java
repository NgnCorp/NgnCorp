package Preload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Scanner;
import mail.SendMail;
import ngn.controller.ReadWI;
import ngn.model.DB;
import ngn.text.Config;
import ngn.text.Paths;
import ngn.text.Text;
import static ngn.text.Text.*;
import static ngn.view.BeforeStart.BSLoadingText;

public class Update {

    private static final Double VER = 0.02;

    private static final String URL = Config.URL;
    private static final String USER = Config.USER;
    private static final String PASS = Config.PASS;
    private static final String KEYWORD = "ver";
    private static URL con;

    public static void Update() {
        BSLoadingText.setText(h1CheckUpdate);
        if (BackendTimers.InternetCheck) {
            BSLoadingText.setText(InetOkTryDownload);
            try {
                BSLoadingText.setText(authSUCS);
                con = new URL("ftp://" + USER + ":" + PASS + "@" + URL + "/");
            } catch (MalformedURLException ex) {
                SendMail.sendEmail(String.valueOf(ex), Text.cantConn + " " + DB.MODULENAME);
                BSLoadingText.setText(cantConn);
            }
            try {
                Scanner scan = new Scanner(con.openStream());
                while (scan.hasNext()) {
                    String line = scan.nextLine();
                    if (line.contains(KEYWORD)) {
                        String ZipVer = line.substring(line.length() - 8, line.length() - 4);
                        String ZipName = KEYWORD + line.substring(line.length() - 9, line.length());
                        CheckNewVersion(ZipVer, ZipName);
                    }
                }
            } catch (IOException ex) {
                BSLoadingText.setText(authNOT);
                SendMail.sendEmail(String.valueOf(ex), Text.authNOT + " " + DB.MODULENAME);
            }
        } else { // No Internet
            BSLoadingText.setText(tryConnInet);
            BackendTimers.WaitForInternet();
        }
    }

    private static void CheckNewVersion(String ZipVer, String ZipName) {

        if (Double.valueOf(ZipVer) > VER) {// перевірка на нову версію
            String upload = con + ZipName;
            String place = "C:/NgnUpdater/Updates/" + ZipName;
            try {
                BSLoadingText.setText(downlNEW);
                download(upload, place);
            } catch (IOException ex) {
                SendMail.sendEmail(String.valueOf(ex), Text.cantdownlNEW + " " + DB.MODULENAME);
                BSLoadingText.setText(cantdownlNEW);
            }
        } else {
            File file = new File(Paths.TRANSACTIONPATH);
            if (file.exists()) {
                ReadWI.ReadWI();
                Threads.CHECKPORTS();
            } else {
                BSLoadingText.setText(cantFIND + Paths.TRANSACTIONPATH);
                try {
                    if (file.createNewFile()) {
                        BSLoadingText.setText(createFile + Paths.TRANSACTIONPATH);
                        Threads.CHECKPORTS();
                    }
                } catch (IOException ex) {
                    BSLoadingText.setText(cantCREATE + Paths.TRANSACTIONPATH);
                    SendMail.sendEmail(String.valueOf(ex), Text.cantCREATE + " " + Paths.TRANSACTIONPATH + " " + DB.MODULENAME);
                }
            }
        }
    }

    private static void download(String urlStr, String file) throws MalformedURLException, IOException {
        URL url = new URL(urlStr);
        try (ReadableByteChannel rbc = Channels.newChannel(url.openStream()); FileOutputStream fos = new FileOutputStream(file)) {
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        }
        OpenandShut();
    }

    public static void OpenandShut() {
        try {
            Runtime.getRuntime().exec("C:\\NgnUpdater\\dist\\Unzip.exe"); // Запуск програми РОЗАРХІВУВАННЯ
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        } catch (IOException ex) {
            BSLoadingText.setText(cantRunProg);
            SendMail.sendEmail(String.valueOf(ex), Text.cantRunProg + " " + DB.MODULENAME);
            System.out.println(ex);
        }
        Runtime.getRuntime().exit(0);
    }
}
