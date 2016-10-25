package ngn.view;

import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import ngn.controller.Listener;
import static ngn.view.Card.CardCode;

/**
 *
 * @author Офис
 */
public class ChoseOil {

    public static javax.swing.JPanel ChoseOil;
    public static javax.swing.JLabel Gas;
    public static javax.swing.JLabel Petrol;
    public static javax.swing.JLabel DieselFuel;
    public static javax.swing.JLabel ChoIl;

    public ChoseOil(JPanel JF) {
        
        ChoseOil = new javax.swing.JPanel();
        Gas = new javax.swing.JLabel();
        Petrol = new javax.swing.JLabel();
        DieselFuel = new javax.swing.JLabel();
        ChoIl = new javax.swing.JLabel();

        Css.cssChoseOil(ChoseOil, ChoIl, Gas, Petrol, DieselFuel);
        
        JF.add(ChoseOil);
        
        CardCode.addActionListener((ActionEvent evt) -> {
            //Listener.FuelType(evt);
        });
    }
}
