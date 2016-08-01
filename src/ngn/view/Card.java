package ngn.view;

import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import ngn.controller.Listener;

/**
 *
 * @author Офис
 */
public class Card {

    private final javax.swing.JPanel EnterCard;
    private final javax.swing.JPasswordField CardCode;
    private final javax.swing.JLabel CardAnimate;
    private final javax.swing.JLabel CardText;
    
    public Card(JFrame JF) {

        EnterCard = new javax.swing.JPanel();
        CardCode = new javax.swing.JPasswordField();
        CardAnimate = new javax.swing.JLabel();
        CardText = new javax.swing.JLabel();
        
        Css.cssCard(EnterCard, CardCode, CardAnimate, CardText);
        CardAnimate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/firstAnim.gif"))); // NOI18N

        JF.add(EnterCard);
        
        CardCode.addActionListener(new Listener.CardCodeActionPerformed());
        CardCode.addFocusListener(new Listener.CheckFocus());
    }
}
