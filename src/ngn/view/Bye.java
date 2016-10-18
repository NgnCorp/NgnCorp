package ngn.view;

import javax.swing.JPanel;

/**
 *
 * @author Офис
 */
public class Bye {
        
        public static javax.swing.JPanel GoodBye;
        public static javax.swing.JLabel ThankYou;
        
    public Bye(JPanel JF){
        GoodBye = new javax.swing.JPanel();
        ThankYou = new javax.swing.JLabel();
        
        Css.cssBye(GoodBye, ThankYou);
        JF.add(GoodBye);
    }
}
