package ngn.controller;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import ngn.view.Bye;
import ngn.view.Card;
import ngn.view.Litrs;
import ngn.view.Pin;
import ngn.view.Wait;
import ngn.view.Work;

/**
 *
 * @author Офис
 */
public class Variables {
    static JPanel[] PanelArray = new JPanel[] {
        Card.EnterCard, 
        Pin.EnterPin, 
        Litrs.EnterLitrs,
        Work.Working,
        Wait.Waiting,
        Bye.GoodBye
    };
    
    static JPasswordField[] InputArray = new JPasswordField[] {
        Card.CardCode,
        Pin.PinCode            
    };
    
}
