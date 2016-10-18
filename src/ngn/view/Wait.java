package ngn.view;

import javax.swing.JPanel;

/**
 *
 * @author Офис
 */
public class Wait {

    public static javax.swing.JPanel Waiting;
    public static javax.swing.JLabel Here;
    public static javax.swing.JLabel WaitingSeconds;

    public Wait(JPanel JF) {

        Waiting = new javax.swing.JPanel();
        Here = new javax.swing.JLabel();
        WaitingSeconds = new javax.swing.JLabel();

        Css.cssWait(Waiting, Here, WaitingSeconds);

        JF.add(Waiting);

    }
}
