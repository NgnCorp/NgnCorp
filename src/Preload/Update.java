package Preload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Scanner;
import ngn.Ngn;
import ngn.controller.ReadWI;
import static ngn.text.Text.*;
import static ngn.view.BeforeStart.BSLoadingText;

public class Update {
    
    private static final Double VER = 0.02;

    private static final String URL = "aftjokers.esy.es";//daystar.ftp.ukraine.com.ua, aftjokers.esy.es
    private static final String USER = "u940880703";//daystar_alex, u940880703
    private static final String PASS = "saniyaext_25";//3mni537k, saniyaext_25
    private static final String KEYWORD = "ver";
    private static URL con;
    
    public static void Update() {
        BSLoadingText.setText(h1CheckUpdate);
        try {
            BSLoadingText.setText(authSUCS);
            con = new URL("ftp://" + USER + ":" + PASS + "@" + URL + "/");
        } catch (MalformedURLException ex) {
            BSLoadingText.setText(cantConn);
            System.out.println(ex);
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
            System.out.println(ex);
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
                System.out.println(ex);
            }
        } else {
            File file = new File(ReadWI.PATH);
            if (file.exists()) {
                ReadWI.ReadWI();
                Ngn.CheckPorts.start();
            } else {
                BSLoadingText.setText(cantFIND + ReadWI.PATH);
                try {
                    if (file.createNewFile()) {
                        BSLoadingText.setText(createFile + ReadWI.PATH);
                        Ngn.CheckPorts.start();
                    }
                } catch (IOException ex) {
                    BSLoadingText.setText(cantCREATE + ReadWI.PATH);
                }
            }
        }
    }

    private static void download(String urlStr, String file) throws IOException {
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
            System.out.println(ex);
        }
        Runtime.getRuntime().exit(0);
    }
}