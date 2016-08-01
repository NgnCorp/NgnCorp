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

        if (PreLoader.PreLoader()) {
            Card card = new Card(Ngn);
            Pin pin = new Pin();
            Litrs litrs = new Litrs();
        }
    }
}
