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
        public static javax.swing.JLabel ClockImg;
        
    public Wait(JFrame JF) {
        
        Waiting = new javax.swing.JPanel();
        Here = new javax.swing.JLabel();
        WaitingSeconds = new javax.swing.JLabel();
        ClockImg = new javax.swing.JLabel();
        
        Css.cssWait(Waiting, Here, WaitingSeconds/*, ClockImg*/);
        ClockImg.setIcon(new ImageIcon(getClass().getResource("/images/clock.png"))); // NOI18N
        
        JF.add(Waiting);
        
        Waiting.addFocusListener(new Listener.CheckFocus());

    }
}
