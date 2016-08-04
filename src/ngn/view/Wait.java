package ngn.view;

import javax.swing.JFrame;
import ngn.controller.Listener;

/**
 *
 * @author Офис
 */
public class Wait {
    
        public static javax.swing.JPanel Waiting;
        public static javax.swing.JLabel Here;
        public static javax.swing.JLabel endTimer;
        public static javax.swing.JButton btnYes;
        
    public Wait(JFrame JF) {
        
        Waiting = new javax.swing.JPanel();
        Here = new javax.swing.JLabel();
        endTimer = new javax.swing.JLabel();
        btnYes = new javax.swing.JButton();
        
        Css.cssWait(Waiting, Here, endTimer, btnYes);
        JF.add(Waiting);
        
        btnYes.addActionListener((java.awt.event.ActionEvent evt) -> {
            //btnYesActionPerformed(evt);
        });
        
        Waiting.addFocusListener(new Listener.CheckFocus());

    }
}
