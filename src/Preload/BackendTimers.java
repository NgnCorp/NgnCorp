package Preload;

import java.awt.event.ActionEvent;
import javax.swing.Timer;
import ngn.Ngn;
import static java.awt.EventQueue.invokeLater;
import jssc.SerialPortException;
import static ngn.controller.ChangePanel.CheckVisibility;
import ngn.controller.CmdReload;
import ngn.controller.KeyPad;
import ngn.controller.ReadWI;
import ngn.text.Paths;
import ngn.text.Text;
import static ngn.view.BeforeStart.BSLoadingPanel;
import static ngn.text.Text.LDBdone;
import static ngn.text.Text.cantConnInet;
import static ngn.text.Text.h1SettingsDone;
import static ngn.view.BeforeStart.BSLoadingText;
import ngn.view.Card;
import static ngn.view.Footer.CardDate;

/**
 *
 * @author Валерик
 */
public class BackendTimers {

    public static Timer AppStart;
    public static Timer WaitForAnswer;
    public static Timer KyePadWorks;
    public static Timer KyePadNotWorks;
    public static Timer LocalDBUpdate;
    public static Timer LocalDBUpdateFast;
    public static Timer WaitForInternet;
    public static Timer InternetStatus;
    public static Timer ReloadSystem;
    public static Timer FastReloadSystem;
    public static Timer WaitForServer;

    public static boolean InternetCheck;
    public static int ConnectionCount = 0;

    Integer LDBTime = 10 * 60 * 1000;//10 * 60 * 1000 = 30 минут
    Integer ServerTime = 5 * 60 * 1000;
    Integer LDBTimeFast = 2 * 60 * 1000;

    public BackendTimers() {
        AppStart = new Timer(1000, (ActionEvent e) -> {

            if (BSLoadingText.getText().equals(h1SettingsDone)) {
                invokeLater(() -> {
                    Ngn.AppContent();
                    BSLoadingPanel.setVisible(false);
                    AppStart.stop();
                });
            } else {
                AppStart.restart();
            }
        });

        WaitForAnswer = new Timer(1000, (ActionEvent e) -> {
            if (BSLoadingText.getText().equals(LDBdone)) {
                BSLoadingText.setText(h1SettingsDone);
                WaitForAnswer.stop();
            } else {
                WaitForAnswer.restart();
            }
        });

        KyePadWorks = new Timer(1000, (ActionEvent e) -> {
            try {
                Boolean TestSignal = KeyPad.KeyPadCOM4.writeString("00");
                if (!TestSignal) {
                    Ngn.StatusBar(Paths.KEYPADOFF, 2);
                    KyePadWorks.stop();
                    KyePadNotWorks.restart();
                } else {
                    Ngn.StatusBar(Paths.KEYPADON, 2);
                }
            } catch (SerialPortException ex) {
                Ngn.StatusBar(Paths.KEYPADOFF, 2);
                System.out.println(ex);
            }
        });

        KyePadNotWorks = new Timer(1000, (ActionEvent e) -> {
            KeyPad.KeyPadSettings();
            if (KeyPad.KeyPadCOM4.isOpened()) {
                KyePadWorks.restart();
                KyePadNotWorks.stop();
            }
        });

        LocalDBUpdate = new Timer(LDBTime, (ActionEvent e) -> {
            if (CheckVisibility().equals("EnterCard") && InternetCheck) {
                ReadWI.ReadWI();
                Threads.LOCALDB();
                Ngn.StatusBar(Paths.SERVERON, 4);
                Ngn.StatusBar(Paths.SERVERCON, 5);
            } else {
                LocalDBUpdateFast.restart();
                LocalDBUpdate.stop();
                Ngn.StatusBar(Paths.SERVEROFF, 4);
            }
        });

        LocalDBUpdateFast = new Timer(LDBTimeFast, (ActionEvent e) -> {
            if (CheckVisibility().equals("EnterCard") && InternetCheck) {
                ReadWI.ReadWI();
                Threads.LOCALDB();
                LocalDBUpdate.restart();
                LocalDBUpdateFast.stop();
                Ngn.StatusBar(Paths.SERVERON, 4);
                Ngn.StatusBar(Paths.SERVERCON, 5);
            } else {
                LocalDBUpdateFast.restart();
            }
        });

        WaitForInternet = new Timer(5000, (ActionEvent e) -> {
            if (InternetCheck) {
                WaitForInternet.stop();
                Threads.UPD();
            } else {
                ConnectionCount++;
                BSLoadingText.setText(cantConnInet);
                if (ConnectionCount >= 2) {
                    ConnectionCount = 0;
                    WaitForInternet.stop();
                    Threads.CHECKPORTS();//No Internet                   
                }
            }
        });

        InternetStatus = new Timer(5000, (ActionEvent e) -> {
            InternetCheck = InternetConn.InternetConn();
            if (!InternetCheck) {
                Ngn.StatusBar(Paths.INETOFF, 1);
                InternetStatus.setDelay(60 * 1000);
            } else {
                InternetStatus.restart();
                Ngn.StatusBar(Paths.INETON, 1);
            }
        });

        ReloadSystem = new Timer(5000, (ActionEvent e) -> {
            if (CardDate.getText().contains(Text.TimeToReload)
                    && BackendTimers.InternetCheck
                    && CheckVisibility().equals("EnterCard")) {
                CmdReload.CmdReload();
            }
        });

        FastReloadSystem = new Timer(5000, (ActionEvent e) -> {
                CmdReload.CmdReload();
        });

        WaitForServer = new Timer(ServerTime, (ActionEvent e) -> {
            if (BackendTimers.InternetCheck
                    && CheckVisibility().equals("EnterCard")
                    && Update.CheckServer()) {
                CmdReload.CmdReload();
            }
        });
    }

    public static void AppStart() {
        AppStart.restart();
    }

    public static void WaitForAnswer() {
        WaitForAnswer.restart();
    }

    public static void LocalDBUpdate() {
        LocalDBUpdate.restart();
    }

    public static void WaitForInternet() {
        WaitForInternet.restart();
    }

    public static void InternetStatus() {
        InternetStatus.start();
    }

    public static void ReloadSystem() {
        ReloadSystem.restart();
    }

    public static void FastReloadSystem() {
        FastReloadSystem.restart();
    }

    public static void WaitForServer() {
        WaitForServer.restart();
    }
}
