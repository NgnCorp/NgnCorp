package ngn.view;

import javax.swing.JFrame;

/**
 *
 * @author Офис
 */
public class Bye {
        
        public static javax.swing.JPanel GoodBye;
        public static javax.swing.JLabel ThankYou;
        
    public Bye(JFrame JF){
        GoodBye = new javax.swing.JPanel();
        ThankYou = new javax.swing.JLabel();
        
        Css.cssBye(GoodBye, ThankYou);
        JF.add(GoodBye);
    }
}
