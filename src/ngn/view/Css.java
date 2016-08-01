package ngn.view;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
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

        EnterCard.setBackground(new Color(204, 0, 0));
        EnterCard.setLayout(new GridBagLayout());

        CardCode.setFont(new Font("Tahoma", 0, 24)); // NOI18N
        CardCode.setHorizontalAlignment(JTextField.CENTER);
        CardCode.setName("CardCode");
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 324;
        gridBagConstraints.ipady = 24;
        EnterCard.add(CardCode, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        EnterCard.add(CardAnimate, gridBagConstraints);

        CardText.setFont(new Font("Candara", 1, 48)); // NOI18N
        CardText.setForeground(new Color(255, 255, 255));
        CardText.setHorizontalAlignment(SwingConstants.CENTER);
        CardText.setText("Поднесите карту к клавиатуре");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        EnterCard.add(CardText, gridBagConstraints);
    }
    
    static void cssPin(JPanel EnterPin, JPasswordField PinCode, JLabel PinAnimate, JLabel PinCodeText, JLabel MarkPinCodeExit, JLabel MarkPinCodeEnter) {
        
        EnterPin.setBackground(new Color(204, 0, 0));
        EnterPin.setLayout(new GridBagLayout());

        PinCode.setFont(new Font("Tahoma", 0, 24)); // NOI18N
        PinCode.setHorizontalAlignment(JTextField.CENTER);
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 270;
        gridBagConstraints.ipady = 10;
        EnterPin.add(PinCode, gridBagConstraints);
        PinCode.setFocusable(true); // false!!!!!!!
        
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        EnterPin.add(PinAnimate, gridBagConstraints);

        PinCodeText.setFont(new Font("Candara", 1, 48));
        PinCodeText.setForeground(new Color(255, 255, 255));
        PinCodeText.setHorizontalAlignment(SwingConstants.CENTER);
        PinCodeText.setText("<html>Введите PIN-код");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(0, 125, 0, 125);
        EnterPin.add(PinCodeText, gridBagConstraints);

        MarkPinCodeExit.setBackground(new Color(204, 0, 0));
        MarkPinCodeExit.setFont(new Font("Candara", 1, 24)); // NOI18N
        MarkPinCodeExit.setForeground(new Color(255, 255, 255));
        MarkPinCodeExit.setHorizontalAlignment(SwingConstants.LEFT);
        MarkPinCodeExit.setText("Для ВЫХОДА нажмите звездочку (*) на клавиатуре.");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        EnterPin.add(MarkPinCodeExit, gridBagConstraints);

        MarkPinCodeEnter.setBackground(new Color(204, 0, 0));
        MarkPinCodeEnter.setFont(new Font("Candara", 1, 24)); // NOI18N
        MarkPinCodeEnter.setForeground(new Color(255, 255, 255));
        MarkPinCodeEnter.setHorizontalAlignment(SwingConstants.LEFT);
        MarkPinCodeEnter.setText("Для ПОДТВЕРЖДЕНИЯ pin-кода нажмите решетку (#) на клавиатуре.");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        EnterPin.add(MarkPinCodeEnter, gridBagConstraints);
    }
}
