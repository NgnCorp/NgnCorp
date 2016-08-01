package ngn.view;

import javax.swing.*;
import java.awt.event.ActionEvent;


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
    
    public Litrs() {
        
        EnterLitrs = new javax.swing.JPanel();
        LitrsInputMark = new javax.swing.JLabel();
        LitrsInput = new javax.swing.JTextField();
        ClientLitrs = new javax.swing.JLabel();
        LitrsMark = new javax.swing.JLabel();
    
        Css.Css(EnterLitrs);
        EnterLitrs.setLayout(new java.awt.GridBagLayout());

        LitrsInputMark.setText("<html><p>Введите количество литров</p>"); // Контент

        LitrsInput.setText("7");
        LitrsInput.addActionListener((ActionEvent evt) -> {
            //LitrsInputActionPerformed(evt);
        });

        //LitrsInput.addFocusListener(new CustomListener());
    }
}
