package Preload;

import java.net.*;

/**
 *
 * @author Валерик
 */
public class InternetConn {

    public static boolean InternetConn() {
        Boolean result = false;
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) new URL("http://google.com/").openConnection();
            con.setRequestMethod("HEAD");
            result = (con.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
        } finally {
            if (con != null) {
                try {
                    con.disconnect();
                } catch (Exception e) {
                }
            }
        }
        return result;
    }
}