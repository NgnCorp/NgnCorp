package ngn.view;

import javax.swing.JPanel;

/**
 *
 * @author Офис
 */
public class Load {

    public static javax.swing.JPanel LoadingPanel;
    public static javax.swing.JLabel LoadingText;
    public static javax.swing.JProgressBar LoadingBar;

    public Load(JPanel JF) {

        LoadingPanel = new javax.swing.JPanel();
        LoadingText = new javax.swing.JLabel();
        LoadingBar = new javax.swing.JProgressBar();

        Css.cssLoad(LoadingPanel, LoadingText, LoadingBar);
        JF.add(LoadingPanel);
    }
}
