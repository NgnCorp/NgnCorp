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
    java.awt.GridBagConstraints gridBagConstraints;
    public Card(JFrame JF) {

        EnterCard = new javax.swing.JPanel();
        CardCode = new javax.swing.JPasswordField();
        CardAnimate = new javax.swing.JLabel();
        CardText = new javax.swing.JLabel();

        EnterCard.setBackground(new java.awt.Color(204, 0, 0));
        EnterCard.setLayout(new java.awt.GridBagLayout());

        CardCode.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        CardCode.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        CardCode.setToolTipText("");
        CardCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //CardCodeActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 324;
        gridBagConstraints.ipady = 24;
        EnterCard.add(CardCode, gridBagConstraints);

        CardAnimate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/firstAnim.gif"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        EnterCard.add(CardAnimate, gridBagConstraints);

        CardText.setFont(new java.awt.Font("Candara", 1, 48)); // NOI18N
        CardText.setForeground(new java.awt.Color(255, 255, 255));
        CardText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CardText.setText("Поднесите карту к клавиатуре");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        EnterCard.add(CardText, gridBagConstraints);

        JF.add(EnterCard);
    }
}
