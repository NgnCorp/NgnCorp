package ngn.view;

import javax.swing.JFrame;
import ngn.controller.Listener;

/**
 *
 * @author Офис
 */
public class Wait {
    
        private final javax.swing.JPanel Waiting;
        private final javax.swing.JLabel Here;
        private final javax.swing.JLabel endTimer;
        private final javax.swing.JButton btnYes;
        
    public Wait(JFrame JF) {
        
        Waiting = new javax.swing.JPanel();
        Here = new javax.swing.JLabel();
        endTimer = new javax.swing.JLabel();
        btnYes = new javax.swing.JButton();
        
        Css.cssWait(Waiting, Here, endTimer, btnYes);
        JF.add(Waiting);
        btnYes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //btnYesActionPerformed(evt);
            }
        });
        Waiting.addFocusListener(new Listener.CheckFocus());

    }
}
