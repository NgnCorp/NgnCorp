package ngn.controller;

import java.io.*;

public class CmdReload {

    public static void CmdReload() throws Exception {
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "shutdown.exe /r /t 0");
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream(), "CP866"));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) {
                break;
            }
            System.out.println(line);
        }
    }
}
