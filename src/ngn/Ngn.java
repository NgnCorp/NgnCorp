package ngn;

import Preload.BackendTimers;
import Preload.PortCheck;
import Preload.Threads;
import java.awt.Color;
import java.awt.Dimension;
import static java.awt.EventQueue.invokeLater;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import ngn.view.*;
import ngn.controller.*;
import ngn.text.Paths;

/**
 *
 * @author Офис
 */
public class Ngn extends JFrame {

    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static JFrame MFRAME = new JFrame();
    static JPanel STATUSBAR = new JPanel();
    static JPanel NGN = new JPanel();
    static JPanel FOOTER = new JPanel();

    public static void main(String[] args) throws InterruptedException {

        invokeLater(() -> {
            // Frames //
            Css.MainFrame(MFRAME, STATUSBAR, NGN, FOOTER);
            Paths PATHS = new Paths();//write paths to images on StatusBar
            MFRAME.add(STATUSBAR);
            MFRAME.add(NGN);
            MFRAME.add(FOOTER);

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

            // App Panels //
            Card CARD = new Card(NGN);
            ChoseOil CHOSEOIL = new ChoseOil(NGN);
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

    public static void StatusBar(URL src, Integer position) {
        Graphics g = STATUSBAR.getGraphics();
        Image img = new ImageIcon(src).getImage();
        int marginL = (screenSize.width - 670) / 2;
        int iconW = 32;
        int iconH = 32;
        int Yposition = screenSize.height/4 - iconH;
        //g.drawString("HELLO WORLD", 300, 10);
        switch (position) {
            case 1: //internet
                g.clearRect(marginL + 10, Yposition, iconW, iconH);
                g.drawImage(img, marginL + 10, Yposition, new Color(204, 0, 0), null);
                break;
            case 2: //keypad
                g.clearRect(marginL + 52, Yposition, iconW, iconH);
                g.drawImage(img, marginL + 52, Yposition, new Color(204, 0, 0), null);
                break;
            case 3: //pistol
                g.clearRect(marginL + 94, Yposition, iconW, iconH);
                g.drawImage(img, marginL + 94, Yposition, new Color(204, 0, 0), null);
                break;
            case 4: //server
                g.clearRect(marginL + 136, Yposition, iconW, iconH);
                g.drawImage(img, marginL + 136, Yposition, new Color(204, 0, 0), null);
                break;
            case 5: //sync DB
                g.clearRect(marginL + 178, Yposition, iconW, iconH);
                g.drawImage(img, marginL + 178, Yposition, new Color(204, 0, 0), null);
                break;
            case 6: //clear sync
                g.setColor(new Color(204, 0, 0));
                g.fillRect(marginL + 178, Yposition, iconW, iconH);
                break;
        }
    }
}
