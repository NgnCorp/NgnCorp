package ngn.controller;

import ngn.view.Pin;

/**
 *
 * @author Валерик
 */
public class ChangePanel {

    public static void ToPin() {      
        for (int i = 0; i < Variables.PanelArray.length; i++) {
            Variables.PanelArray[i].setVisible(false);
        }        
        Pin.EnterPin.setVisible(true);
    }

}
