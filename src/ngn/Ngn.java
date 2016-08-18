package ngn;

import Preload.PreLoader;
import java.awt.Cursor;
import static java.awt.EventQueue.invokeLater;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.image.MemoryImageSource;
import javax.swing.JFrame;
import javax.swing.Timer;
import ngn.view.*;
import ngn.controller.*;
import static ngn.view.BeforeStart.LoadingText;

/**
 *
 * @author Офис
 */
public class Ngn extends JFrame {

    static Timer AppStart;

    public static void main(String[] args) {

        JFrame NGN = new JFrame();

        invokeLater(() -> {
            // Frames //
            //Css.MainFrame(NGN);
            PreLoader.PreLoader();
            BeforeStart BEFORESTART = new BeforeStart(NGN);
        });

        AppStart = new Timer(1000, (ActionEvent e) -> {
            
            int[] pixels = new int[16 * 16];
            Image image = Toolkit.getDefaultToolkit().createImage(
                    new MemoryImageSource(16, 16, pixels, 0, 16));
            Cursor transparentCursor = Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(0, 0), "invisibleCursor");
            NGN.setCursor(transparentCursor);

            if (LoadingText.getText().equals("Настройка завершена")) {
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
                });
            } else {
                AppStart.restart();
            }
        });
        AppStart.start();
    }
}
