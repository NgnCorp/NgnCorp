package ngn.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Офис
 */
public class Info {
    
        public static JPanel InfoMassage;
        public static JLabel ErrorMassage;
        public static JLabel ServerTimer;
        
    public Info(JFrame JF) {
        
        InfoMassage = new JPanel();
        ErrorMassage = new JLabel();
        ServerTimer = new JLabel();
        
        Css.cssInfo(InfoMassage, ErrorMassage, ServerTimer);
        JF.add(InfoMassage);
    }
}
