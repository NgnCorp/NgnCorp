package ngn.controller;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CmdReload {

    public static void CmdReload() {
            ProcessBuilder builder = new ProcessBuilder(
                    "cmd.exe", "/c", "taskkill /f /im javaw.exe");
            builder.redirectErrorStream(true);
            Process p;
            try {
                p = builder.start();
                BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream(), "CP866"));
                String line;
                while (true) {
                    line = r.readLine();
                    if (line == null) {
                        break;
                    }
                    System.out.println(line);
                }
            } catch (IOException ex) {
                Logger.getLogger(CmdReload.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
