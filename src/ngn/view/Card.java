package ngn.view;

import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import ngn.controller.Listener;
import ngn.text.Paths;

/**
 *
 * @author Офис
 */
public class Card {

    public static javax.swing.JPanel EnterCard;
    public static javax.swing.JPasswordField CardCode;
    public static javax.swing.JLabel CardAnimate;
    public static javax.swing.JLabel CardText;
    public static javax.swing.JLabel CardSignature;
    public static javax.swing.JLabel CardDate;

    public Card(JPanel JF) {

        EnterCard = new javax.swing.JPanel();
        CardCode = new javax.swing.JPasswordField();
        CardAnimate = new javax.swing.JLabel();
        CardText = new javax.swing.JLabel();
        CardSignature = new javax.swing.JLabel();
        CardDate = new javax.swing.JLabel();

        Css.cssCard(EnterCard, CardCode, CardAnimate, CardText, CardSignature, CardDate);
        CardAnimate.setIcon(new ImageIcon(getClass().getResource("/images/firstAnim.gif"))); // NOI18N
        CardSignature.setIcon(new ImageIcon(getClass().getResource("/images/Developer.png")));

        JF.add(EnterCard);

        CardCode.addActionListener((ActionEvent evt) -> {
            Listener.CardCodeAction(evt);
        });
    }
}
