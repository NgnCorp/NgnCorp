package ngn.controller;

import javax.swing.JPanel;
import static ngn.view.Card.EnterCard;
import static ngn.view.Pin.EnterPin;

/**
 *
 * @author Валерик
 */
public class ChangePanel {

    public static void ChangePanel(JPanel Close, JPanel Show) {
        Close.setVisible(false);
        //Close.setFocusable(false);
        Show.setVisible(true);
        //Show.requestFocusInWindow();
    }
    
}
