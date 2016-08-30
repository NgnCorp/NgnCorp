package ngn;

import Preload.BackendTimers;
import Preload.PortCheck;
import Preload.Threads;
import static java.awt.EventQueue.invokeLater;
import javax.swing.JFrame;
import ngn.view.*;
import ngn.controller.*;

/**
 *
 * @author Офис
 */
public class Ngn extends JFrame {

    static JFrame NGN = new JFrame();

    public static void main(String[] args) throws InterruptedException {
        
        invokeLater(() -> {
            // Frames //
            Css.MainFrame(NGN);

            // Backend Controllers //
            BeforeStart BEFORESTART = new BeforeStart(NGN);
            BackendTimers BACKENDTIMERS = new BackendTimers();
            Threads THREADS = new Threads();
            //Threads.UPD();  //Start with COM ports check
            BackendTimers.LocalDBUpdate();
            AppContent(); PortCheck.GSPort = "COM3"; PortCheck.KPPort = "COM4"; ReadWI.CreateLocalDB();  //Start without COM ports check
            ReadWI.ReadWI();
        });
    }

    public static void AppContent() {

        invokeLater(() -> {
            Card CARD   = new Card(NGN);
            Pin PIN     = new Pin(NGN);
            Litrs LITRS = new Litrs(NGN);
            Work WORK   = new Work(NGN);
            Wait WAIT   = new Wait(NGN);
            Info INFO   = new Info(NGN);
            Load LOAD   = new Load(NGN);
            Bye BYE     = new Bye(NGN);

            // Controllers //
            KeyPad KEYPAD           = new KeyPad();
            GasStation GASSTATION   = new GasStation();
            Listener ACTIONLISTENER = new Listener();
            Timers TIMER            = new Timers();
            Variables VARIABLES     = new Variables();

            ChangePanel.ShowPanel(Card.EnterCard);
            ChangePanel.FocusPassword(Card.CardCode);
        });
    }
}
