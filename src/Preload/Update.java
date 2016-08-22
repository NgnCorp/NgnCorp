package Preload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import static ngn.text.Text.h1CheckUpdate;
import static ngn.view.BeforeStart.LoadingText;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import ngn.Ngn;
import ngn.controller.ReadWI;
import org.apache.commons.net.ftp.FTPReply;

public class Update {

    private static final Double VER = 0.10;
    private static final String SERVER = "aftjokers.esy.es";//"daystar.ftp.ukraine.com.ua";
    private static final int PORT = 21;
    private static final String USER = "u940880703";//"daystar_alex";
    private static final String PASS = "saniyaext_25";//"3mni537k";
    private static String nameOfVer;
    private static Boolean checkNewVers = false;
    private static String vers;

    /*    private static void showServerReply(FTPClient ftpClient) {
        String[] replies = ftpClient.getReplyStrings();
        if (replies != null && replies.length > 0) {
            for (String aReply : replies) {
                System.out.println("SERVER: " + aReply);
            }
        }
    }*/
    public static void Update() throws InterruptedException {
        LoadingText.setText(h1CheckUpdate);
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(SERVER, PORT); // підключення до серверу
            //showServerReply(ftpClient);
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                LoadingText.setText("Не удается подключится к серверу!");
            }
            boolean success = ftpClient.login(USER, PASS); // підключення до акаунта
            //showServerReply(ftpClient);
            if (!success) {
                LoadingText.setText("Авторизация не пройдена!");
            } else {
                LoadingText.setText("Авторизация пройшла успешно!");
                list(ftpClient);
            }
        } catch (IOException ex) {
            System.out.println("Не удалось подключиться к серверу!");
        }
    }

    public static void OpenandShut() throws InterruptedException {
        try {
            Runtime.getRuntime().exec(ReadWI.PATH); // Запуск програми РОЗАРХІВУВАННЯ
            Thread.sleep(500);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        Runtime.getRuntime().exit(0);
    }

    public static void list(FTPClient ftpClient) throws InterruptedException, IOException {
        try {
            FTPFile[] files = ftpClient.listFiles();
            for (FTPFile ftpFile : files) {
                nameOfVer = ftpFile.getName();
                if (ftpFile.getType() == FTPFile.FILE_TYPE) {
                    if ("ver".equals(nameOfVer.substring(0, 3))) {
                        if (Double.valueOf(nameOfVer.substring(4, 8)) > VER) { // перевірка на нову версію
                            checkNewVers = true;
                            vers = nameOfVer;
                        } else {
                            System.out.println("Can't find new version");
                        }
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
        if (checkNewVers) {
            try {
                download("http://" + SERVER + "/" + vers, "C:\\Updater\\Updates\\" + vers); // Шлях до нової версії програми
                LoadingText.setText("Скачивание новой версии программы");
                OpenandShut();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        } else {
            File file = new File(ReadWI.PATH);
            System.out.println(file.exists());
            if (file.exists()) {
                ReadWI.ReadWI();
                Ngn.CheckPorts.start();
            } else {
                LoadingText.setText("Не удается найти файл: " + ReadWI.PATH);
                if (file.createNewFile()) {
                    Ngn.CheckPorts.start();
                } else {
                    LoadingText.setText("Не удается создать файл: " + ReadWI.PATH);
                }
            }
        }
    }
// Скачування нової версії програми

    private static void download(String server, String file) throws IOException {
        URL url = new URL(server);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(file);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
    }
}
