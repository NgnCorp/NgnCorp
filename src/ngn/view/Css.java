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
        MFrame.pack();
        MFrame.setSize(windowWidth, windowHeight);
        MFrame.setLocationRelativeTo(null);
        MFrame.setVisible(true);
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
        
        CardCode.setFocusable(true);
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
        
        EnterPin.setVisible(false); // Hide panel after render
    }
    
    static void cssLitrs(JLabel ActionExitText, JLabel ActionEnterText, JLabel CardMark, JLabel NameMark, JLabel ClientCard, JLabel ClientName, JPanel ClientInfo, JLabel LogoNgn, JPanel EnterLitrs, JLabel LitrsInputMark, JTextField LitrsInput, JLabel ClientLitrs, JLabel LitrsMark) {
        EnterLitrs.setBackground(new Color(204, 0, 0));
        EnterLitrs.setLayout(new GridBagLayout());

        LitrsInputMark.setFont(new Font("Candara", 0, 36)); // NOI18N
        LitrsInputMark.setForeground(new Color(255, 255, 255));
        LitrsInputMark.setHorizontalAlignment(SwingConstants.CENTER);
        LitrsInputMark.setText("<html><p>Введите количество литров</p>");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.PAGE_END;
        EnterLitrs.add(LitrsInputMark, gridBagConstraints);

        LogoNgn.setFont(new Font("Candara", 1, 18));
        LogoNgn.setForeground(new Color(255, 153, 51));
        LogoNgn.setHorizontalAlignment(SwingConstants.CENTER);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        EnterLitrs.add(LogoNgn, gridBagConstraints);

        LitrsInput.setFont(new Font("Candara", 1, 72)); // NOI18N
        LitrsInput.setHorizontalAlignment(JTextField.CENTER);
        LitrsInput.setText("");

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        EnterLitrs.add(LitrsInput, gridBagConstraints);

        ClientInfo.setBackground(new Color(204, 0, 0));
        ClientInfo.setLayout(new GridBagLayout());

        ClientName.setBackground(new Color(204, 0, 0));
        ClientName.setFont(new Font("Candara", 1, 24)); // NOI18N
        ClientName.setForeground(new Color(255, 255, 255));
        ClientName.setHorizontalAlignment(SwingConstants.LEFT);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 10;
        ClientInfo.add(ClientName, gridBagConstraints);

        ClientLitrs.setBackground(ClientName.getBackground());
        ClientLitrs.setFont(ClientName.getFont());
        ClientLitrs.setForeground(ClientName.getForeground());
        ClientLitrs.setHorizontalAlignment(ClientName.getHorizontalAlignment());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 10;
        ClientInfo.add(ClientLitrs, gridBagConstraints);

        ClientCard.setBackground(ClientName.getBackground());
        ClientCard.setFont(ClientName.getFont());
        ClientCard.setForeground(ClientName.getForeground());
        ClientCard.setHorizontalAlignment(ClientName.getHorizontalAlignment());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 10;
        ClientInfo.add(ClientCard, gridBagConstraints);

        NameMark.setFont(new Font("Candara", 1, 18)); // NOI18N
        NameMark.setForeground(new Color(255, 153, 51));
        NameMark.setText("Владелец карты:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        ClientInfo.add(NameMark, gridBagConstraints);

        LitrsMark.setFont(NameMark.getFont());
        LitrsMark.setForeground(NameMark.getForeground());
        LitrsMark.setText("Остаток литров:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        ClientInfo.add(LitrsMark, gridBagConstraints);

        CardMark.setFont(NameMark.getFont());
        CardMark.setForeground(NameMark.getForeground());
        CardMark.setText("Номер карты:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        ClientInfo.add(CardMark, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        EnterLitrs.add(ClientInfo, gridBagConstraints);

        ActionEnterText.setFont(new Font("Candara", 1, 24)); // NOI18N
        ActionEnterText.setForeground(new Color(255, 255, 255));
        ActionEnterText.setText("Нажмите решетку (#) на клавиатуре для ПУСКА заправки.");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(10, 0, 10, 0);
        EnterLitrs.add(ActionEnterText, gridBagConstraints);

        ActionExitText.setBackground(ActionEnterText.getBackground());
        ActionExitText.setFont(ActionEnterText.getFont());
        ActionExitText.setForeground(ActionEnterText.getForeground());
        ActionExitText.setHorizontalAlignment(ActionEnterText.getHorizontalAlignment());
        ActionExitText.setText("Нажмите звездочку (*) на клавиатуре для ВЫХОДА.");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new Insets(10, 0, 10, 0);
        EnterLitrs.add(ActionExitText, gridBagConstraints);
        
        EnterLitrs.setVisible(false); // Hide panel after render
    }
    static void cssWork(JPanel Working, JLabel PolozheniePistoleta, JLabel SchetLitrov, JLabel MoneySchetLitrov, JLabel MarkSchetLitrov, JLabel MarkMoneySchetLitrov, JLabel Reklama) {
        
        Working.setBackground(new Color(204, 0, 0));
        Working.setLayout(new GridBagLayout());

        PolozheniePistoleta.setBackground(Working.getBackground());
        PolozheniePistoleta.setFont(new Font("Candara", 1, 24)); // NOI18N
        PolozheniePistoleta.setForeground(new Color(255, 255, 255));
        PolozheniePistoleta.setHorizontalAlignment(SwingConstants.CENTER);
        PolozheniePistoleta.setToolTipText("");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.insets = new Insets(20, 0, 0, 0);
        Working.add(PolozheniePistoleta, gridBagConstraints);

        SchetLitrov.setBackground(PolozheniePistoleta.getBackground());
        SchetLitrov.setFont(new Font("Candara", 1, 48)); // NOI18N
        SchetLitrov.setForeground(PolozheniePistoleta.getForeground());
        SchetLitrov.setHorizontalAlignment(PolozheniePistoleta.getHorizontalAlignment());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        Working.add(SchetLitrov, gridBagConstraints);

        MoneySchetLitrov.setBackground(PolozheniePistoleta.getBackground());
        MoneySchetLitrov.setFont(new Font("Candara", 1, 48)); // NOI18N
        MoneySchetLitrov.setForeground(PolozheniePistoleta.getForeground());
        MoneySchetLitrov.setHorizontalAlignment(PolozheniePistoleta.getHorizontalAlignment());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.EAST;
        gridBagConstraints.insets = new Insets(0, 50, 0, 0);
        Working.add(MoneySchetLitrov, gridBagConstraints);

        MarkSchetLitrov.setBackground(PolozheniePistoleta.getBackground());
        MarkSchetLitrov.setFont(PolozheniePistoleta.getFont());
        MarkSchetLitrov.setForeground(PolozheniePistoleta.getForeground());
        MarkSchetLitrov.setHorizontalAlignment(PolozheniePistoleta.getHorizontalAlignment());
        MarkSchetLitrov.setText("СЧЕТ ЛИТРОВ");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        gridBagConstraints.insets = new Insets(0, 0, 20, 0);
        Working.add(MarkSchetLitrov, gridBagConstraints);

        MarkMoneySchetLitrov.setBackground(PolozheniePistoleta.getBackground());
        MarkMoneySchetLitrov.setFont(PolozheniePistoleta.getFont());
        MarkMoneySchetLitrov.setForeground(PolozheniePistoleta.getForeground());
        MarkMoneySchetLitrov.setHorizontalAlignment(PolozheniePistoleta.getHorizontalAlignment());
        MarkMoneySchetLitrov.setText("ГРН:");
        MarkMoneySchetLitrov.setToolTipText("");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        Working.add(MarkMoneySchetLitrov, gridBagConstraints);

        Reklama.setBackground(PolozheniePistoleta.getBackground());
        Reklama.setFont(PolozheniePistoleta.getFont());
        Reklama.setForeground(PolozheniePistoleta.getForeground());
        Reklama.setHorizontalAlignment(PolozheniePistoleta.getHorizontalAlignment());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.insets = new Insets(0, 0, 0, 50);
        Working.add(Reklama, gridBagConstraints);
        
        Working.setVisible(false); // Hide panel after render
    } 
    static void cssBye(JPanel GoodBye, JLabel ThankYou) {
        GoodBye.setBackground(new Color(204, 0, 0));
        GoodBye.setLayout(new GridBagLayout());

        ThankYou.setBackground(new Color(204, 0, 0));
        ThankYou.setFont(new Font("Candara", 1, 48));
        ThankYou.setForeground(new Color(255, 255, 255));
        ThankYou.setHorizontalAlignment(SwingConstants.CENTER);
        ThankYou.setText("<html><p style=\"text-align:center;\">Транзакция прошла успешно!<br>Спасибо, что воспользовались услугой компании NGN!</p>");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        GoodBye.add(ThankYou, gridBagConstraints);
        
        GoodBye.setVisible(false); // Hide panel after render
    }
}
