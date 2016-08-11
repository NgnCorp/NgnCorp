package ngn.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Офис
 */
public class Work {

    public static JPanel Working;
    public static JLabel PolozheniePistoleta;
    public static JLabel SchetLitrov;
    public static JLabel MoneySchetLitrov;
    public static JLabel MarkSchetLitrov;
    public static JLabel MarkMoneySchetLitrov;
    public static JLabel Reklama;

    public Work(JFrame JF) {

        Working = new javax.swing.JPanel();
        PolozheniePistoleta = new javax.swing.JLabel();
        SchetLitrov = new javax.swing.JLabel();
        MoneySchetLitrov = new javax.swing.JLabel();
        MarkSchetLitrov = new javax.swing.JLabel();
        MarkMoneySchetLitrov = new javax.swing.JLabel();
        Reklama = new JLabel();

        Css.cssWork(Working, PolozheniePistoleta, SchetLitrov, MoneySchetLitrov, MarkSchetLitrov, MarkMoneySchetLitrov, Reklama);
        
        Reklama.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reklama.gif"))); // NOI18N

        JF.add(Working);
    }
}
