package ngn;

import static java.awt.EventQueue.invokeLater;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.Timer;
import ngn.view.*;
import ngn.controller.*;
import static ngn.view.BeforeStart.LoadingText;

/**
 *
 * @author Офис
 */
public class Ngn extends JFrame {

    static Timer AppStart;

    public static void main(String[] args) {
        
        JFrame NGN = new JFrame();
        
        invokeLater(() -> {
            // Frames //
            Css.MainFrame(NGN);
            BeforeStart BEFORESTART = new BeforeStart(NGN);
        });

        AppStart = new Timer(1000, (ActionEvent e) -> {

            if (LoadingText.getText().equals("Настройка завершена")) {
                invokeLater(() -> {

                    Card CARD = new Card(NGN);
                    Pin PIN = new Pin(NGN);
                    Litrs LITRS = new Litrs(NGN);
                    Work WORK = new Work(NGN);
                    Wait WAIT = new Wait(NGN);
                    Info INFO = new Info(NGN);
                    Load LOAD = new Load(NGN);
                    Bye BYE = new Bye(NGN);

                    // Controllers //
                    KeyPad KEYPAD = new KeyPad();
                    GasStation GASSTATION = new GasStation();
                    Listener ACTIONLISTENER = new Listener();
                    Timers TIMER = new Timers();
                    Variables VARIABLES = new Variables();
                });
            } else {
                AppStart.restart();
            }
        });
        AppStart.start();
    }
}
