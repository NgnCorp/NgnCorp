package Preload;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class Update {

    private static final Double VER = 0.00;
    private static final String SERVER = "daystar.ftp.ukraine.com.ua";
    private static final int PORT = 21;
    private static final String USER = "daystar_alex";
    private static final String PASS = "3mni537k";
    private static String nameOfVer;

    private static void showServerReply(FTPClient ftpClient) {
        String[] replies = ftpClient.getReplyStrings();
        if (replies != null && replies.length > 0) {
            for (String aReply : replies) {
                System.out.println("SERVER: " + aReply);
            }
        }
    }

    public static void Update() {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(SERVER, PORT); // підключення до серверу
            showServerReply(ftpClient);
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                System.out.println("Невозможно подключится к серверу: " + replyCode);
            }
            boolean success = ftpClient.login(USER, PASS); // підключення до акаунта
            showServerReply(ftpClient);
            if (!success) {
                System.out.println("Авторизация не пройдена!");
            } else {
                System.out.println("Авторизированы!");
                list(ftpClient);
            }
        } catch (IOException ex) {
            System.out.println("Щось сталось!");
        }
    }

    public static void list(FTPClient ftpClient) {
        try {
            FTPFile[] files = ftpClient.listFiles();
            for (FTPFile ftpFile : files) {
                nameOfVer = ftpFile.getName();
                if (ftpFile.getType() == FTPFile.FILE_TYPE) {
                    if ("ver".equals(nameOfVer.substring(0, 3))) {
                        if (Double.valueOf(nameOfVer.substring(4, 8)) < VER) { // перевірка на нову версію
                            System.out.println("Can't find new version");
                        } else {
                            try {
                                download("http://"+SERVER+"/"+nameOfVer, "Data\\"+nameOfVer); // Шлях до нової версії програми
                            } catch (IOException ex) {
                                
                            }
                        }
                    }
                }
            }
        } catch (IOException ex) {
            
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