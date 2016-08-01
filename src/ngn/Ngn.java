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
        JFrame Ngn = new JFrame();
        Css.MainFrame(Ngn);

        //if (PreLoader.PreLoader()) {
            Card CARD = new Card(Ngn);
            Pin PIN = new Pin();
            Litrs LITRS = new Litrs();
        //}
    }
}
