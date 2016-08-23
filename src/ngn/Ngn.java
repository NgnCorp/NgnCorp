package ngn;

import Preload.BackendTimers;
import Preload.PreLoader;
import Preload.Update;
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
    static Runnable runCheckPorts;
    static Runnable runUpdate;
    public static Thread CheckPorts;
    public static Thread Upd;

    public static void main(String[] args) throws InterruptedException {
        NgnPreload(); //Start with COM ports check
        invokeLater(() -> {
            // Frames //
            Css.MainFrame(NGN);

            // Backend Controllers //
            BeforeStart BEFORESTART = new BeforeStart(NGN);
            BackendTimers BACKENDTIMERS = new BackendTimers();
            Upd = new Thread(runUpdate);
            Upd.start();
            CheckPorts = new Thread(runCheckPorts);
            //AppContent(); //Start without COM ports check
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

            ChangePanel.ShowPanel(Card.EnterCard);
            ChangePanel.FocusPassword(Card.CardCode);
        });
    }

    public static void NgnPreload() {
        runUpdate = () -> {
            Update.Update();
        };
        runCheckPorts = () -> {
            // Settings for App //
            PreLoader.PreLoader();
        };
    }
}
