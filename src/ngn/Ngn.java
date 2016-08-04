package ngn;

import javax.swing.JFrame;
import ngn.view.*;
import ngn.controller.*;
import ngn.text.*;
import ngn.model.*;
import static ngn.view.Pin.EnterPin;

/**
 *
 * @author Офис
 */
public class Ngn extends JFrame {

    public static void main(String[] args) {
        if (PreLoader.PreLoader()) {
            
            // Controllers //
            
            KeyPad KEYPAD = new KeyPad();
            GasStation GASSTATION = new GasStation();
            Listener ACTIONLISTENER = new Listener();
            Timer TIMER = new Timer();
            Variables VARIABLES = new Variables();
            
            // Frames //
            
            JFrame NGN = new JFrame();
            Css.MainFrame(NGN);
            
            //Litrs LITRS = new Litrs(NGN);
            Card CARD = new Card(NGN);
            //Pin PIN = new Pin(NGN);
            //Work WORK = new Work(NGN);
            
            ChangePanel.ChangePanel(Pin.EnterPin, Card.EnterCard);
        }
    }
}
