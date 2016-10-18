package ngn.view;

import java.awt.event.ActionEvent;
import javax.swing.*;
import ngn.controller.Listener;


/**
 *
 * @author Офис
 */
public class Litrs {
    
    public static JPanel EnterLitrs;
    public static JLabel ClientLitrs;
    public static JLabel LitrsInputMark;
    public static JLabel LitrsMark;
    public static JTextField LitrsInput;
    public static JLabel LogoNgn;
    public static JPanel ClientInfo;
    public static JLabel ClientName;
    public static JLabel ClientCard;
    public static JLabel NameMark;
    public static JLabel CardMark;
    public static JLabel ActionEnterText;
    public static JLabel ActionExitText;
    
    public Litrs(JPanel JF) {
        
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
        
        LitrsInput.addActionListener((ActionEvent evt) -> {
            Listener.LitrsInputAction(evt);
        });
    }
}
