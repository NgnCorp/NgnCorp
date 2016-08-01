package ngn.view;

import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import ngn.view.Css;

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

        CardCode.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        CardCode.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        CardCode.setToolTipText("");
        CardCode.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CardCodeActionPerformed(evt);
            }

            private void CardCodeActionPerformed(ActionEvent evt) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        Css.Css(EnterCard);
        JF.add(EnterCard);
    }
}
