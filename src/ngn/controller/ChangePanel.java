package ngn.controller;

import javax.swing.JPanel;
import ngn.view.Card;
import ngn.view.Pin;

/**
 *
 * @author Валерик
 */
public class ChangePanel {

    public static void ChangePanel(JPanel Close, JPanel Show) {
        Close.setVisible(false);
        Close.setFocusable(false);
        Show.setVisible(true);
        Show.setFocusable(true);
    }
    
}
