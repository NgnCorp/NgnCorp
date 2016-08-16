package ngn.view;

import javax.swing.JFrame;

/**
 *
 * @author Офис
 */
public class BeforeStart {

    public static javax.swing.JPanel LoadingPanel;
    public static javax.swing.JLabel LoadingText;
    public static javax.swing.JProgressBar LoadingBar;

    public BeforeStart(JFrame JF) {

        LoadingPanel = new javax.swing.JPanel();
        LoadingText = new javax.swing.JLabel();
        LoadingBar = new javax.swing.JProgressBar();

        Css.cssBeforeStart(LoadingPanel, LoadingText, LoadingBar);
        JF.add(LoadingPanel);
    }
}
