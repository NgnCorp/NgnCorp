package ngn.controller;

import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 *
 * @author Валерик
 */
public class ChangePanel {

    public static void ShowPanel(JPanel Show) {
        for (JPanel PanelArray : Variables.PanelArray) {
            PanelArray.setVisible(false);
        }
        Show.setVisible(true);
    }

    public static void ShowPanel(JPasswordField Show) {
        for (JPasswordField InputArray : Variables.InputArray) {
            InputArray.setFocusable(false);
        }
        Show.setFocusable(true);
    }

}
