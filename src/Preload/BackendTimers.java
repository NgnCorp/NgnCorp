package Preload;

import java.awt.event.ActionEvent;
import javax.swing.Timer;
import ngn.Ngn;
import static java.awt.EventQueue.invokeLater;
import jssc.SerialPortException;
import ngn.controller.KeyPad;
import static ngn.view.BeforeStart.BSLoadingPanel;
import static ngn.text.Text.PortsON;
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

    Integer LDBTime = 10 * 1000;//30 * 60 * 1000 = 30 минут

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
            if (BSLoadingText.getText().equals(PortsON)) {
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
            Threads.LOCALDB();
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
}
