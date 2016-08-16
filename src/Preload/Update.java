package Preload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class Update {

    private static ArrayList<File> listWithFileNames = new ArrayList<>();
    private static final Double ver = 0.00;
    private static final String server = "www.aftjokers.esy.es";
    private static final int port = 21;
    private static final String user = "u940880703";
    private static final String pass = "saniyaext_25";

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
            ftpClient.connect(server, port);
            showServerReply(ftpClient);
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                System.out.println("Невозможно подключится к серверу: " + replyCode);
            }
            boolean success = ftpClient.login(user, pass);
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
                if (ftpFile.getType() == FTPFile.FILE_TYPE) {
                    System.out.println("File: " + ftpFile.getName().substring(0, 3));
                    if ("ver".equals(ftpFile.getName().substring(0, 3))) {
                        if (Double.valueOf(ftpFile.getName().substring(4, 8)) < ver) {
                            System.out.println("Can't find new version");
                        } else {
                            try {
                                download("http://"+server.substring(4)+"/"+ftpFile.getName(), "update\\"+ftpFile.getName());
                            } catch (IOException ex) {
                                
                            }
                        }
                    }
                }
            }
        } catch (IOException ex) {
            
        }
    }

    private static void download(String server, String file) throws IOException {
        URL url = new URL(server);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(file);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
    }
}