package ngn.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import ngn.Ngn;

/**
 *
 * @author Офис
 */
public class Css extends Ngn {
    
    static java.awt.GridBagConstraints gridBagConstraints;
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int windowHeight = screenSize.height;
    public static int windowWidth = screenSize.width;
    
    public Css() {
        
    }
    
    public static void MainFrame(JFrame MFrame) {
        MFrame.setDefaultCloseOperation(MFrame.EXIT_ON_CLOSE);

        MFrame.setUndecorated(true);
        //window.pack();
        MFrame.setSize(windowWidth, windowHeight);
        //window.setLocationRelativeTo(null);
        MFrame.setVisible(true);
    }
    
    public static void JPanel(JPanel JP) {
        JP.setSize(windowWidth, windowHeight);
        JP.setBackground(new Color(204, 0, 0));
    }

    static void cssCard(JPanel EnterCard, JPasswordField CardCode, JLabel CardAnimate, JLabel CardText) {

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
        
    }
    
}
