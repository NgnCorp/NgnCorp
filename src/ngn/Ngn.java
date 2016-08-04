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
                
                // Controllers //

                KeyPad KEYPAD = new KeyPad();
                GasStation GASSTATION = new GasStation();
                Listener ACTIONLISTENER = new Listener();
                Timer TIMER = new Timer();
                Variables VARIABLES = new Variables();

                // Frames //
                JFrame NGN = new JFrame();
                Css.MainFrame(NGN);
                
                //Card CARD = new Card(NGN);
                //Pin PIN = new Pin(NGN);
                //Litrs LITRS = new Litrs(NGN);
                //Work WORK = new Work(NGN);
                //Wait WAIT = new Wait(NGN);
                Info INFO = new Info(NGN);
            });
        }
    }
}
