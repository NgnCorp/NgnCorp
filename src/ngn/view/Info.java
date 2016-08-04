package ngn.view;

import javax.swing.JFrame;
import ngn.controller.Listener;

/**
 *
 * @author Офис
 */
public class Info {
    
        public static javax.swing.JPanel InfoMassage;
        public static javax.swing.JLabel ErrorMassage;
        public static javax.swing.JLabel ServerTimer;
        
    public Info(JFrame JF) {
        
        InfoMassage = new javax.swing.JPanel();
        ErrorMassage = new javax.swing.JLabel();
        ServerTimer = new javax.swing.JLabel();
        
        Css.cssInfo(InfoMassage, ErrorMassage, ServerTimer);
        JF.add(InfoMassage);
        
        InfoMassage.addFocusListener(new Listener.CheckFocus());
    }
}
