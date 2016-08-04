package ngn.view;

import javax.swing.JFrame;

/**
 *
 * @author Офис
 */
public class Bye {
        
        private final javax.swing.JPanel GoodBye;
        private final javax.swing.JLabel ThankYou;
        
    public Bye(JFrame JF){
        GoodBye = new javax.swing.JPanel();
        ThankYou = new javax.swing.JLabel();
        
        Css.cssBye(GoodBye, ThankYou);
        JF.add(GoodBye);
    }
}
