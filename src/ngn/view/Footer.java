package ngn.view;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Офис
 */
public class Footer {

    public static javax.swing.JLabel CardSignature;
    public static javax.swing.JLabel CardDate;

    public Footer(JPanel JF) {

        CardSignature = new javax.swing.JLabel();
        CardDate = new javax.swing.JLabel();

        Css.cssFooter(JF, CardSignature, CardDate);
        CardSignature.setIcon(new ImageIcon(getClass().getResource("/images/Developer.png")));

    }
}