package Preload;

import static Preload.PortCheck.ClosePorts;
import static Preload.PortCheck.GSPort;
import static Preload.PortCheck.KPPort;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import ngn.Ngn;
import static java.awt.EventQueue.invokeLater;
import static ngn.view.BeforeStart.BSLoadingPanel;
import static ngn.view.BeforeStart.BSLoadingText;

/**
 *
 * @author Валерик
 */
public class BackendTimers {

    public static Timer AppStart;
    public static Timer WaitForAnswer;

    public BackendTimers() {
        AppStart = new Timer(1000, (ActionEvent e) -> {

            if (BSLoadingText.getText().equals("Настройка завершена")) {
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
            if (GSPort != null && KPPort != null) {
                ClosePorts();
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
