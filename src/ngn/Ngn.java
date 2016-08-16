package ngn;

import Preload.PreLoader;
import static java.awt.EventQueue.invokeLater;
import javax.swing.JFrame;
import ngn.view.*;
import ngn.controller.*;

/**
 *
 * @author Офис
 */
public class Ngn extends JFrame {

    public static void main(String[] args) {

        // Frames //
        JFrame NGN = new JFrame();
        Css.MainFrame(NGN);
        BeforeStart BEFORESTART = new BeforeStart(NGN);
        
        if (PreLoader.PreLoader()) {
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
        }
    }
}
