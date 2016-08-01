package ngn.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import ngn.Ngn;

/**
 *
 * @author Офис
 */
public class Css extends Ngn {
    
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int windowHeight = screenSize.height;
    public static int windowWidth = screenSize.width;
    
    public static void MainFrame(JFrame MFrame) {
        MFrame.setDefaultCloseOperation(MFrame.EXIT_ON_CLOSE);

        MFrame.setUndecorated(true);
        //window.pack();
        MFrame.setSize(windowWidth, windowHeight);
        //window.setLocationRelativeTo(null);
        MFrame.setVisible(true);
    }
    public static void Css(JPanel JP) {
        JP.setSize(windowWidth, windowHeight);
        JP.setBackground(Color.BLACK);
    }
}
