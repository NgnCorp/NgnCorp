package ngn.controller;

import ngn.view.Pin;

/**
 *
 * @author Валерик
 */
public class ChangePanel {

    public static void ToPin() { 
            System.out.println(Variables.PanelArray.length);       
        for (int i = 0; i < Variables.PanelArray.length; i++) {
            System.out.println(Variables.PanelArray[0]);
        }        
        //Pin.EnterPin.setVisible(true);
    }

}
