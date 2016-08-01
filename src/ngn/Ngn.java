package ngn;

import javax.swing.JFrame;
import ngn.view.*;
import ngn.controller.*;
import ngn.text.*;
import ngn.model.*;

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
            ActionListener ACTIONLISTENER = new ActionListener();
            Timer TIMER = new Timer();
            Variables VARIABLES = new Variables();
            
            // Frames //
            
            JFrame NGN = new JFrame();
            Litrs LITRS = new Litrs();
            Pin PIN = new Pin(NGN);
            Card CARD = new Card(NGN);
            
            // Styles //
            
            Css.MainFrame(NGN);
        }
    }
}
