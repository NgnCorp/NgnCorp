package ngn.view;

import javax.swing.JFrame;
import ngn.controller.Listener;

/**
 *
 * @author Офис
 */
public class Pin {
    
    private final javax.swing.JPanel EnterPin;
    private final javax.swing.JLabel MarkPinCodeEnter;
    private final javax.swing.JLabel MarkPinCodeExit;
    private final javax.swing.JLabel PinAnimate;
    private final javax.swing.JPasswordField PinCode;
    private final javax.swing.JLabel PinCodeText;
    
    public Pin(JFrame JF) {
        
        EnterPin = new javax.swing.JPanel();
        PinCode = new javax.swing.JPasswordField();
        PinAnimate = new javax.swing.JLabel();
        PinCodeText = new javax.swing.JLabel();
        MarkPinCodeExit = new javax.swing.JLabel();
        MarkPinCodeEnter = new javax.swing.JLabel();
        
        Css.cssPin(EnterPin, PinCode, PinAnimate, PinCodeText, MarkPinCodeExit, MarkPinCodeEnter);
        PinAnimate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/secAnim.gif"))); // NOI18N
        
        PinCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //PinCodeActionPerformed(evt);
            }
        });
        
        PinCode.addFocusListener(new Listener.CheckFocus());
        
        JF.add(EnterPin);
    }
}
