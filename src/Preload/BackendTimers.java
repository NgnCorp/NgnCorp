package Preload;

import java.awt.event.ActionEvent;
import javax.swing.Timer;
import ngn.Ngn;
import static java.awt.EventQueue.invokeLater;
import jssc.SerialPortException;
import static ngn.controller.ChangePanel.CheckVisibility;
import ngn.controller.KeyPad;
import ngn.controller.ReadWI;
import static ngn.view.BeforeStart.BSLoadingPanel;
import static ngn.text.Text.LDBdone;
import static ngn.text.Text.cantConnInet;
import static ngn.text.Text.h1SettingsDone;
import static ngn.view.BeforeStart.BSLoadingText;

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
    public static Timer WaitForInternet;
    public static Timer InternetStatus;
    public static boolean InternetCheck;

    Integer LDBTime = 1 * 60 * 1000;//30 * 60 * 1000 = 30 минут
    static Integer Delay = 1000;//30 * 60 * 1000 = 30 минут

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
                    KyePadWorks.stop();
                    KyePadNotWorks.restart();
                }
            } catch (SerialPortException ex) {
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
                LocalDBUpdate.setDelay(1 * 60 * 1000);//Back to normal time
            } else {
                LocalDBUpdate.setDelay(15000);
                LocalDBUpdate.restart();
            }
        });

        WaitForInternet = new Timer(3000, (ActionEvent e) -> {
            if (InternetCheck) {
                WaitForInternet.stop();
                Update.Update();
            } else {
                BSLoadingText.setText(cantConnInet);
                WaitForInternet.restart();
            }
        });

        InternetStatus = new Timer(1000, (ActionEvent e) -> {
            InternetCheck = InternetConn.InternetConn();
            InternetStatus();
            System.out.println(InternetStatus.getDelay());
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
        InternetStatus.setDelay(Delay);
        Delay += 3000;
        InternetStatus.start();
    }
}
