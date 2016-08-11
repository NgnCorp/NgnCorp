package ngn.controller;

import java.util.Arrays;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import ngn.view.Litrs;

/**
 *
 * @author Валерик
 */
public class ChangePanel {

    public static void ShowPanel(JPanel Show) {
        for (JPanel PanelArray : Variables.PanelArray) {
            PanelArray.setVisible(false);
            PanelArray.setFocusable(false);
        }
        Show.setVisible(true);
    }

    public static void FocusPassword(JPasswordField Show) {
        for (JPasswordField InputArray : Variables.InputArray) {
            InputArray.setFocusable(false);
        }
        Show.setFocusable(true);
        Show.requestFocusInWindow();
    }

    public static void FocusLitrsInput() {
        for (JPasswordField InputArray : Variables.InputArray) {
            InputArray.setFocusable(false);
        }
        Litrs.LitrsInput.setFocusable(true);
        Litrs.LitrsInput.requestFocusInWindow();
    }

    public static String CheckVisibility() {
        String VisiblePanel = "";
        for (JPanel PanelArray : Variables.PanelArray) {
            if (PanelArray.isVisible()) {
                VisiblePanel = PanelArray.getName();
            }
        }
        return VisiblePanel;
    }
}
