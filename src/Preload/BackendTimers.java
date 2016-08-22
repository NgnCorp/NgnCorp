package Preload;

import java.awt.event.ActionEvent;
import javax.swing.Timer;
import ngn.Ngn;
import static ngn.text.Text.PortsON;
import static ngn.text.Text.h1SettingsDone;
import static ngn.view.BeforeStart.LoadingPanel;
import static ngn.view.BeforeStart.LoadingText;

/**
 *
 * @author Валерик
 */
public class BackendTimers {

    public static Timer AppStart;
    public static Timer WaitForAnswer;

    public BackendTimers() {
        AppStart = new Timer(1000, (ActionEvent e) -> {

            if (LoadingText.getText().equals(h1SettingsDone)) {
                Ngn.AppContent();
                AppStart.stop();
            } else {
                AppStart.restart();
            }
            System.out.println("AppStart Timer Works");
        });

        WaitForAnswer = new Timer(1000, (ActionEvent e) -> {
            if (LoadingText.getText().equals(PortsON)) {
                //ClosePorts();
                LoadingText.setText(h1SettingsDone);
            } else {
                WaitForAnswer.restart();
            }
        });
    }

    public static void AppStart() {
        AppStart.restart();
    }

    public static void WaitForAnswer() {
        WaitForAnswer.restart();
    }
}
