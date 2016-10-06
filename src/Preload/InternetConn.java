package Preload;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;

/**
 *
 * @author Валерик
 */
public class InternetConn {

    public static boolean InternetConn() {
        Boolean result = false;
        try {
            URL url = new URL("http://google.com/");
            InputStream inputStream = url.openStream();
            BufferedReader reader;
            reader = new BufferedReader(new InputStreamReader(inputStream, "windows-1251"));
            StringBuilder allText = new StringBuilder();
            char[] buff = new char[1];

            int count;
            while ((count = reader.read(buff)) != -1) {
                allText.append(buff, 0, count);
            }
            Integer indStart = allText.indexOf("<title>");
            Integer indEnd = allText.indexOf("</title>", indStart);
            String google = allText.substring(indStart + 7, indEnd);
            if ("Google".equals(google)) {
                result = true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return result ;
    }
}
