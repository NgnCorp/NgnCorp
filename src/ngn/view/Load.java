package ngn.view;

import javax.swing.JFrame;

/**
 *
 * @author Офис
 */
public class Load {
    
        private final javax.swing.JPanel LoadingPanel;
        private final javax.swing.JLabel LoadingText;
        private final javax.swing.JProgressBar LoadingBar;
        
        public Load(JFrame JF) {
            
            LoadingPanel = new javax.swing.JPanel();
            LoadingText = new javax.swing.JLabel();
            LoadingBar = new javax.swing.JProgressBar();
            
            Css.cssLoad(LoadingPanel, LoadingText, LoadingBar);
            JF.add(LoadingPanel);
        }
}
