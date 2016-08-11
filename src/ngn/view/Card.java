package ngn.view;

import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import ngn.controller.Listener;

/**
 *
 * @author Офис
 */
public class Card {

    public static javax.swing.JPanel EnterCard;
    public static javax.swing.JPasswordField CardCode;
    public static javax.swing.JLabel CardAnimate;
    public static javax.swing.JLabel CardText;

    public Card(JFrame JF) {

        EnterCard = new javax.swing.JPanel();
        CardCode = new javax.swing.JPasswordField();
        CardAnimate = new javax.swing.JLabel();
        CardText = new javax.swing.JLabel();

        Css.cssCard(EnterCard, CardCode, CardAnimate, CardText);
        CardAnimate.setIcon(new ImageIcon(getClass().getResource("/images/firstAnim.gif"))); // NOI18N   

        JF.add(EnterCard);

        CardCode.addActionListener((ActionEvent evt) -> {
            Listener.CardCodeAction(evt);
        });
    }
}
