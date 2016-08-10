package ngn.view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import ngn.controller.Listener;

/**
 *
 * @author Офис
 */
public class Wait {
    
        public static javax.swing.JPanel Waiting;
        public static javax.swing.JLabel Here;
        public static javax.swing.JLabel WaitingSeconds;
        
    public Wait(JFrame JF) {
        
        Waiting = new javax.swing.JPanel();
        Here = new javax.swing.JLabel();
        WaitingSeconds = new javax.swing.JLabel();
        
        Css.cssWait(Waiting, Here, WaitingSeconds);
        
        JF.add(Waiting);
        
        Waiting.addFocusListener(new Listener.CheckFocus());

    }
}
