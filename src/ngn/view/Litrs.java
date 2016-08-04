package ngn.view;

import javax.swing.*;
import ngn.controller.Listener;


/**
 *
 * @author Офис
 */
public class Litrs {
    
    private final JPanel EnterLitrs;
    private final JLabel ClientLitrs;
    private final JLabel LitrsInputMark;
    private final JLabel LitrsMark;
    private final JTextField LitrsInput;
    private final JLabel LogoNgn;
    private final JPanel ClientInfo;
    private final JLabel ClientName;
    private final JLabel ClientCard;
    private final JLabel NameMark;
    private final JLabel CardMark;
    private final JLabel ActionEnterText;
    private final JLabel ActionExitText;
    
    public Litrs(JFrame JF) {
        
        EnterLitrs = new JPanel();
        LitrsInputMark = new JLabel();
        LitrsInput = new JTextField();
        ClientLitrs = new JLabel();
        LitrsMark = new JLabel();
        LogoNgn = new JLabel();
        ClientInfo = new JPanel();
        ClientName = new JLabel();
        ClientCard = new JLabel();
        NameMark = new JLabel();
        CardMark = new JLabel();
        ActionEnterText = new JLabel();
        ActionExitText = new JLabel();
        
        Css.cssLitrs(ActionExitText, ActionEnterText, CardMark, NameMark, ClientCard, ClientName, ClientInfo, LogoNgn, EnterLitrs, LitrsInputMark, LitrsInput, ClientLitrs, LitrsMark);
        LogoNgn.setIcon(new ImageIcon(getClass().getResource("/images/logo_ngn.png"))); // NOI18N
        
        JF.add(EnterLitrs);
        
        LitrsInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //LitrsInputActionPerformed(evt);
            }
        });
        
        LitrsInput.addFocusListener(new Listener.CheckFocus());
    }
}
