package ngn.view;

import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import ngn.controller.Listener;

/**
 *
 * @author Офис
 */
public class Pin {
    
    public static javax.swing.JPanel EnterPin;
    public static javax.swing.JLabel MarkPinCodeEnter;
    public static javax.swing.JLabel MarkPinCodeExit;
    public static javax.swing.JLabel PinAnimate;
    public static javax.swing.JPasswordField PinCode;
    public static javax.swing.JLabel PinCodeText;
    
    public Pin(JPanel JF) {
        
        EnterPin = new javax.swing.JPanel();
        PinCode = new javax.swing.JPasswordField();
        PinAnimate = new javax.swing.JLabel();
        PinCodeText = new javax.swing.JLabel();
        MarkPinCodeExit = new javax.swing.JLabel();
        MarkPinCodeEnter = new javax.swing.JLabel();
        
        Css.cssPin(EnterPin, PinCode, PinAnimate, PinCodeText, MarkPinCodeExit, MarkPinCodeEnter);
        PinAnimate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/secAnim.gif"))); // NOI18N
        
        JF.add(EnterPin);
        
        PinCode.addActionListener((ActionEvent evt) -> {
            Listener.PinCodeAction(evt);
        });
    }
}
