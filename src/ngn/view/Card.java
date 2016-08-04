package ngn.view;

import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import ngn.controller.ChangePanel;
import ngn.controller.Listener;

/**
 *
 * @author Офис
 */
public class Card {

    public static javax.swing.JPanel EnterCard;
    private final javax.swing.JPasswordField CardCode;
    private final javax.swing.JLabel CardAnimate;
    private final javax.swing.JLabel CardText;
    
    public Card(JFrame JF) {

        EnterCard = new javax.swing.JPanel();
        CardCode = new javax.swing.JPasswordField();
        CardAnimate = new javax.swing.JLabel();
        CardText = new javax.swing.JLabel();
        
        JF.add(EnterCard);
        
        Css.cssCard(EnterCard, CardCode, CardAnimate, CardText);
        CardAnimate.setIcon(new ImageIcon(getClass().getResource("/images/firstAnim.gif"))); // NOI18N
        
        JF.add(EnterCard);
        
        CardCode.addActionListener((ActionEvent evt) -> {
            //CardCodeActionPerformed(evt);
        });
        
        CardCode.addFocusListener(new Listener.CheckFocus());
    }
}
