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
        //if (PreLoader.PreLoader()) {
        JFrame Ngn = new JFrame();
        Css.MainFrame(Ngn);
        Card CARD = new Card(Ngn);
        Pin PIN = new Pin();
        Litrs LITRS = new Litrs();
        //}
    }
}
