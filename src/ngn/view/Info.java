package ngn.view;

import javax.swing.JFrame;
import static ngn.view.Card.EnterCard;

/**
 *
 * @author Офис
 */
public class Info {
    
        private final javax.swing.JPanel InfoMassage;
        private final javax.swing.JLabel ErrorMassage;
        private final javax.swing.JLabel ServerTimer;
        private final javax.swing.JPanel LoadingPanel;
        private final javax.swing.JLabel LoadingText;
        private final javax.swing.JProgressBar LoadingBar;
        
    public Info(JFrame JF) {
        
        InfoMassage = new javax.swing.JPanel();
        ErrorMassage = new javax.swing.JLabel();
        ServerTimer = new javax.swing.JLabel();
        LoadingPanel = new javax.swing.JPanel();
        LoadingText = new javax.swing.JLabel();
        LoadingBar = new javax.swing.JProgressBar();
        
        Css.cssInfo(InfoMassage, ErrorMassage, ServerTimer, LoadingPanel, LoadingText, LoadingBar);
        JF.add(InfoMassage);
    }
}
