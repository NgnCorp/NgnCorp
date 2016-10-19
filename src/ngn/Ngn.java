package ngn;

import Preload.BackendTimers;
import Preload.PortCheck;
import Preload.Threads;
import java.awt.Color;
import static java.awt.EventQueue.invokeLater;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import ngn.view.*;
import ngn.controller.*;

/**
 *
 * @author Офис
 */
public class Ngn extends JFrame {

    static JFrame MFRAME = new JFrame();
    static JPanel STATUSBAR = new JPanel();
    static JPanel NGN = new JPanel();

    public static void main(String[] args) throws InterruptedException {

        invokeLater(() -> {
            // Frames //
            Css.MainFrame(MFRAME, STATUSBAR, NGN);
            MFRAME.add(STATUSBAR);
            MFRAME.add(NGN);

            // Backend Controllers //
            BeforeStart BEFORESTART = new BeforeStart(NGN);
            BackendTimers BACKENDTIMERS = new BackendTimers();
            Threads THREADS = new Threads();
            Threads.INTERNETCONN();
            Threads.UPD();  //Full APP start
            BackendTimers.LocalDBUpdate();
            //AppContent(); PortCheck.GSPort = "COM3"; PortCheck.KPPort = "COM4"; //Start without COM ports check
        });
    }

    public static void AppContent() {

        invokeLater(() -> {
            Card CARD = new Card(NGN);
            Pin PIN = new Pin(NGN);
            Litrs LITRS = new Litrs(NGN);
            Work WORK = new Work(NGN);
            Wait WAIT = new Wait(NGN);
            Info INFO = new Info(NGN);
            Load LOAD = new Load(NGN);
            Bye BYE = new Bye(NGN);

            // Controllers //
            KeyPad KEYPAD = new KeyPad();
            GasStation GASSTATION = new GasStation();
            Listener ACTIONLISTENER = new Listener();
            Timers TIMER = new Timers();
            Variables VARIABLES = new Variables();

            Timers.DateTime();
            BackendTimers.ReloadSystem();//Check for reload computer

            ChangePanel.ShowPanel(Card.EnterCard);
            ChangePanel.FocusPassword(Card.CardCode);

            // BAG WITH BS APPEARED OB CARDCODE PANEL //
            BeforeStart.BSLoadingBar.setVisible(false);
            BeforeStart.BSLoadingText.setVisible(false);
        });
    }

    public static void SetActiveNgn() {
        MFRAME.toFront();
    }

    public static void StatusBar(String src, Integer position) {
        Graphics g = STATUSBAR.getGraphics();
        Image img = new ImageIcon(src).getImage();
        System.out.println(src);
        switch (position) {
            case 1: //internet
                g.clearRect(10, 10, 32, 32);
                g.drawImage(img, 10, 10, new Color(204, 0, 0), null);
                break;
            case 2: //keypad
                g.clearRect(52, 10, 32, 32);
                g.drawImage(img, 52, 10, new Color(204, 0, 0), null);
                break;
            case 3: //pistol
                g.clearRect(94, 10, 32, 32);
                g.drawImage(img, 94, 10, new Color(204, 0, 0), null);
                break;
            case 4: //server
                g.clearRect(136, 10, 32, 32);
                g.drawImage(img, 136, 10, new Color(204, 0, 0), null);
                break;
            case 5: //sync DB
                g.clearRect(178, 10, 32, 32);
                g.drawImage(img, 178, 10, new Color(204, 0, 0), null);
                break;
            case 6: //clear sync
                g.setColor(new Color(204, 0, 0));
                g.fillRect(178, 10, 32, 32);
                break;
        }
    }
}
