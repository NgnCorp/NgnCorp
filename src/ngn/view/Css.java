package ngn.view;

import java.awt.*;
import java.awt.image.MemoryImageSource;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import ngn.Ngn;
import ngn.text.Text;

/**
 *
 * @author Офис
 */
public class Css extends Ngn {

    static java.awt.GridBagConstraints gridBagConstraints;
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int windowHeight = screenSize.height;
    public static int windowWidth = screenSize.width;

    public static void MainFrame(JFrame MFrame) {
        MFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MFrame.setUndecorated(true);
        MFrame.pack();
        MFrame.setSize(windowWidth, windowHeight);
        MFrame.setLocationRelativeTo(null);
        MFrame.setLayout(new CardLayout());
        MFrame.setVisible(true);

        int[] pixels = new int[16 * 16];
        Image image = Toolkit.getDefaultToolkit().createImage(
                new MemoryImageSource(16, 16, pixels, 0, 16));
        Cursor transparentCursor = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(0, 0), "invisibleCursor");
        MFrame.setCursor(transparentCursor);
    }

    static void cssCard(JPanel EnterCard, JPasswordField CardCode, JLabel CardAnimate, JLabel CardText, JLabel CardSignature, JLabel CardDate) {
        
        EnterCard.setBackground(new Color(204, 0, 0));
        EnterCard.setLayout(new GridBagLayout());

        CardCode.setFont(new Font("Tahoma", 0, 24)); // NOI18N
        CardCode.setHorizontalAlignment(JPasswordField.CENTER);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        CardCode.setPreferredSize(new Dimension(324,50));
        EnterCard.setName("EnterCard");
        EnterCard.add(CardCode, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        EnterCard.add(CardAnimate, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = CardCode.getWidth();
        gridBagConstraints.ipady = 25;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        CardSignature.setFont(new Font("Candara", 0, 16));
        CardSignature.setForeground(new Color(255, 255, 255));
        CardSignature.setText(Text.SignatureText);
        CardSignature.setHorizontalAlignment(JTextField.LEFT);
        EnterCard.add(CardSignature, gridBagConstraints);

        //System.out.println("Current Date: " + ft.format(date));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipady = CardSignature.getVerticalTextPosition();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        CardDate.setFont(new Font("Candara", 0, 20));
        CardDate.setForeground(new Color(255, 255, 255));
        CardDate.setHorizontalAlignment(JTextField.RIGHT);
        CardDate.setText(Text.BeginingTime);
        EnterCard.add(CardDate, gridBagConstraints);

        CardText.setFont(new Font("Candara", 1, 48)); // NOI18N
        CardText.setForeground(new Color(255, 255, 255));
        CardText.setHorizontalAlignment(SwingConstants.CENTER);
        CardText.setText(Text.h1CardPanel);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        //gridBagConstraints.insets = new Insets(0,0,20,0);
        EnterCard.add(CardText, gridBagConstraints);
    }

    static void cssPin(JPanel EnterPin, JPasswordField PinCode, JLabel PinAnimate, JLabel PinCodeText, JLabel MarkPinCodeExit, JLabel MarkPinCodeEnter) {

        EnterPin.setBackground(new Color(204, 0, 0));
        EnterPin.setLayout(new GridBagLayout());

        PinCode.setFont(new Font("Tahoma", 0, 24)); // NOI18N
        PinCode.setHorizontalAlignment(JPasswordField.CENTER);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        PinCode.setPreferredSize(new Dimension(324,50));
        EnterPin.add(PinCode, gridBagConstraints);

        EnterPin.setName("EnterPin");

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        EnterPin.add(PinAnimate, gridBagConstraints);

        PinCodeText.setFont(new Font("Candara", 1, 48));
        PinCodeText.setForeground(new Color(255, 255, 255));
        PinCodeText.setHorizontalAlignment(SwingConstants.CENTER);
        PinCodeText.setText(Text.h1EnterPin);
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
        MarkPinCodeExit.setText(Text.h1ExitStar);
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
        MarkPinCodeEnter.setText(Text.h1Confirm);
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

        EnterLitrs.setName("EnterLitrs");

        LitrsInputMark.setFont(new Font("Candara", 0, 36)); // NOI18N
        LitrsInputMark.setForeground(new Color(255, 255, 255));
        LitrsInputMark.setHorizontalAlignment(SwingConstants.CENTER);
        LitrsInputMark.setText(Text.h1SetLitrs);
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
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        LitrsInput.setPreferredSize(new Dimension(324,50));
        EnterLitrs.add(LitrsInput, gridBagConstraints);

        ClientInfo.setBackground(new Color(204, 0, 0));
        ClientInfo.setLayout(new GridBagLayout());

        ClientInfo.setName("ClientInfo");

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
        NameMark.setText(Text.h1CardOwner);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        ClientInfo.add(NameMark, gridBagConstraints);

        LitrsMark.setFont(NameMark.getFont());
        LitrsMark.setForeground(NameMark.getForeground());
        LitrsMark.setText(Text.h1LitrsStorage);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.LINE_START;
        ClientInfo.add(LitrsMark, gridBagConstraints);

        CardMark.setFont(NameMark.getFont());
        CardMark.setForeground(NameMark.getForeground());
        CardMark.setText(Text.h1CardNum);
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
        ActionEnterText.setText(Text.h1StartFilling);
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
        ActionExitText.setText(Text.h1ExitStar);
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

        Working.setName("Working");

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
        MarkSchetLitrov.setText(Text.h1CountLitrs);
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
        MarkMoneySchetLitrov.setText(Text.h1UAH);
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

        GoodBye.setName("GoodBye");

        ThankYou.setBackground(new Color(204, 0, 0));
        ThankYou.setFont(new Font("Candara", 1, 48));
        ThankYou.setForeground(new Color(255, 255, 255));
        ThankYou.setHorizontalAlignment(SwingConstants.CENTER);
        ThankYou.setText(Text.h1TYforChoose);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        GoodBye.add(ThankYou, gridBagConstraints);

        GoodBye.setVisible(false); // Hide panel after render
    }

    static void cssWait(JPanel Waiting, JLabel Here, JLabel endTimer) {

        Waiting.setBackground(new Color(204, 0, 0));
        Waiting.setLayout(new GridBagLayout());

        Waiting.setName("Waiting");

        Here.setFont(new Font("Candara", 1, 68));
        Here.setForeground(new Color(255, 255, 255));
        Here.setHorizontalAlignment(SwingConstants.CENTER);
        Here.setText(Text.h1AreUHere);
        Here.setAlignmentX(0.5F);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 296;
        gridBagConstraints.ipady = 50;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;
        Waiting.add(Here, gridBagConstraints);

        endTimer.setFont(new Font("Candara", 1, 28)); // NOI18N
        endTimer.setForeground(new Color(255, 255, 255));
        endTimer.setHorizontalAlignment(SwingConstants.CENTER);
        endTimer.setText(Text.h1ClickIfUHere);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 736;
        gridBagConstraints.ipady = 0;
        gridBagConstraints.anchor = GridBagConstraints.SOUTH;
        Waiting.add(endTimer, gridBagConstraints);

        Waiting.setVisible(false);
    }

    static void cssInfo(JPanel InfoMassage, JLabel ErrorMassage, JLabel ServerTimer) {

        InfoMassage.setBackground(new Color(204, 0, 0));
        InfoMassage.setLayout(new CardLayout());

        InfoMassage.setName("InfoMassage");

        ErrorMassage.setFont(new Font("Candara", 1, 48)); // NOI18N
        ErrorMassage.setForeground(new Color(255, 255, 255));
        ErrorMassage.setHorizontalAlignment(SwingConstants.CENTER);
        ErrorMassage.setToolTipText("");
        InfoMassage.add(ErrorMassage, "card3");

        ServerTimer.setFont(new Font("Candara", 1, 48)); // NOI18N
        ServerTimer.setForeground(new Color(255, 255, 255));
        ServerTimer.setHorizontalAlignment(SwingConstants.CENTER);
        ServerTimer.setToolTipText("");
        InfoMassage.add(ServerTimer, "card3");

        InfoMassage.setVisible(false);
    }

    static void cssLoad(JPanel LoadingPanel, JLabel LoadingText, JProgressBar LoadingBar) {

        LoadingPanel.setVisible(false);
        LoadingPanel.setBackground(new Color(204, 0, 0));
        LoadingPanel.setLayout(new GridBagLayout());

        LoadingPanel.setName("LoadingPanel");

        LoadingText.setFont(new Font("Candara", 1, 24)); // NOI18N
        LoadingText.setForeground(new Color(255, 255, 255));
        LoadingText.setHorizontalAlignment(SwingConstants.CENTER);
        LoadingText.setText(Text.h1LostIntrCon);
        LoadingText.setToolTipText("");
        LoadingPanel.add(LoadingText, new GridBagConstraints());

        LoadingBar.setIndeterminate(true);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 10, 5, 10);
        LoadingPanel.add(LoadingBar, gridBagConstraints);
    }

    static void cssBeforeStart(JPanel BSLoadingPanel, JLabel BSLoadingText, JProgressBar BSLoadingBar) {

        BSLoadingPanel.setVisible(true);
        BSLoadingPanel.setBackground(new Color(204, 0, 0));
        BSLoadingPanel.setLayout(new GridBagLayout());

        BSLoadingPanel.setName("BeforeStartLoadingPanel");

        BSLoadingText.setFont(new Font("Candara", 1, 24)); // NOI18N
        BSLoadingText.setForeground(new Color(255, 255, 255));
        BSLoadingText.setHorizontalAlignment(SwingConstants.CENTER);
        BSLoadingText.setText(Text.h1BeforeStart);
        BSLoadingPanel.add(BSLoadingText, new GridBagConstraints());

        BSLoadingBar.setIndeterminate(true);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 10, 5, 10);

        BSLoadingPanel.add(BSLoadingBar, gridBagConstraints);
    }
}
