package Preload;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

/**
 *
 * @author Валерик
 */
public class InternetConn {

    public static boolean InternetConn() {
        try {
            URL url = new URL("http://www.google.com/");
            
            HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
            urlc.setRequestProperty("User-Agent", "test");
            urlc.setRequestProperty("Connection", "close");
            urlc.setConnectTimeout(2000);
            urlc.setReadTimeout(3000);
            urlc.connect();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlc.getInputStream(), "windows-1251"));
            
            StringBuilder allText = new StringBuilder();
            char[] buff = new char[1];

            int count;
            while ((count = reader.read(buff)) != -1) {
                allText.append(buff, 0, count);
            }
            Integer intStart = allText.indexOf("<title>");
            Integer intEnd = allText.indexOf("</title>", intStart);
            String google = allText.substring(intStart + 7, intEnd);
            System.out.println(google);
            return "Google".equals(google);
        } catch (IOException e) {
            System.out.println("Ошибка проверки подключения к интернету: " + e);
            return false;
        }
    }
}


    /*
    public static boolean InternetConn() {
        Boolean result = false;
        try {
            URL url = new URL("http://google.com/");
            InputStream inputStream = url.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "windows-1251"));
            StringBuilder allText = new StringBuilder();
            char[] buff = new char[1];

            int count;
            while ((count = reader.read(buff)) != -1) {
                allText.append(buff, 0, count);
            }
            Integer intStart = allText.indexOf("<title>");
            Integer intEnd = allText.indexOf("</title>", intStart);
            String google = allText.substring(intStart + 7, intEnd);
            if ("Google".equals(google)) {
                result = true;
            }
        reader.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result ;
    }
     */
