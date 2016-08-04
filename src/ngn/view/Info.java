package ngn.view;

import javax.swing.JFrame;

/**
 *
 * @author Офис
 */
public class Info {
    
        private final javax.swing.JPanel InfoMassage;
        private final javax.swing.JLabel ErrorMassage;
        private final javax.swing.JLabel ServerTimer;
        
    public Info(JFrame JF) {
        
        InfoMassage = new javax.swing.JPanel();
        ErrorMassage = new javax.swing.JLabel();
        ServerTimer = new javax.swing.JLabel();
        
        Css.cssInfo(InfoMassage, ErrorMassage, ServerTimer);
        JF.add(InfoMassage);
    }
}
