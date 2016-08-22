package ngn.view;

import javax.swing.JFrame;

/**
 *
 * @author Офис
 */
public class BeforeStart {

    public static javax.swing.JPanel BSLoadingPanel;
    public static javax.swing.JLabel BSLoadingText;
    public static javax.swing.JProgressBar BSLoadingBar;

    public BeforeStart(JFrame JF) {

        BSLoadingPanel = new javax.swing.JPanel();
        BSLoadingText = new javax.swing.JLabel();
        BSLoadingBar = new javax.swing.JProgressBar();

        Css.cssBeforeStart(BSLoadingPanel, BSLoadingText, BSLoadingBar);
        JF.add(BSLoadingPanel);
    }
}
