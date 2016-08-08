package ngn;

import static java.awt.EventQueue.invokeLater;
import javax.swing.JFrame;
import ngn.view.*;
import ngn.controller.*;
import static ngn.view.Card.EnterCard;

/**
 *
 * @author Офис
 */
public class Ngn extends JFrame {

    public static void main(String[] args) {

        if (PreLoader.PreLoader()) {
            invokeLater(() -> {

                // Frames //
                JFrame NGN = new JFrame();
                Css.MainFrame(NGN);

                Card CARD = new Card(NGN);
                Pin PIN = new Pin(NGN);
                Litrs LITRS = new Litrs(NGN);
                Work WORK = new Work(NGN);
                Wait WAIT = new Wait(NGN);
                Bye BYE = new Bye(NGN);

                // Controllers //
                KeyPad KEYPAD = new KeyPad();
                GasStation GASSTATION = new GasStation();
                Timer TIMER = new Timer();
                Variables VARIABLES = new Variables();
                Listener ACTIONLISTENER = new Listener();
            });
        }
    }
}