package ngn.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import ngn.controller.Listener;

/**
 *
 * @author Офис
 */
public class Filling {

    private final JPanel Working;
    private final JLabel PolozheniePistoleta;
    private final JLabel SchetLitrov;
    private final JLabel MoneySchetLitrov;
    private final JLabel MarkSchetLitrov;
    private final JLabel MarkMoneySchetLitrov;
    private final JLabel Reklama;

    public Filling(JFrame JF) {

        Working = new javax.swing.JPanel();
        PolozheniePistoleta = new javax.swing.JLabel();
        SchetLitrov = new javax.swing.JLabel();
        MoneySchetLitrov = new javax.swing.JLabel();
        MarkSchetLitrov = new javax.swing.JLabel();
        MarkMoneySchetLitrov = new javax.swing.JLabel();
        Reklama = new JLabel();

        Css.cssFilling(Working, PolozheniePistoleta, SchetLitrov, MoneySchetLitrov, MarkSchetLitrov, MarkMoneySchetLitrov, Reklama);
        
        Reklama.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reklama.gif"))); // NOI18N
        
        Working.addFocusListener(new Listener.CheckFocus());

        JF.add(Working);
    }
}
