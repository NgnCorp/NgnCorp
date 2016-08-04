package ngn.controller;

import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ngn.model.*;
import static ngn.view.Load.LoadingPanel;
import ngn.view.Pin;

/**
 *
 * @author Офис
 */
public class Listener {

    public static String ObjectInFocus;

    public static class CheckFocus implements FocusListener {

        public CheckFocus() {

        }

        @Override
        public void focusGained(FocusEvent e) {
            ObjectInFocus = e.getComponent().getName();
            System.out.println(ObjectInFocus);
        }

        @Override
        public void focusLost(FocusEvent e) {

        }
    }

    public static void CardCodeAction(ActionEvent e) {
        String CardCode = e.getActionCommand();
        if (CardCode.length() == 10) {
            if (DB.cardCode(CardCode)) {
                try {
                    Variables.pin            = DB.rs.getString("c.pin");
                    Variables.name           = DB.rs.getString("c.name");
                    Variables.litrnum        = DB.rs.getString("c.litrnum");
                    Variables.code           = DB.rs.getString("c.code");
                    Variables.customerId     = DB.rs.getInt("cu.customer_id");
                    Variables.customerPrice  = DB.rs.getDouble("cu.customer_price");
                    Variables.uahBalance     = DB.rs.getDouble("totalprice");
                    Variables.purse          = DB.rs.getString("purse");
                    Variables.limitDay       = DB.rs.getInt("limit_day");
                    Variables.limitLitrs     = DB.rs.getDouble("limit_litrs");
                    Variables.usedLimitLitrs = DB.rs.getDouble("used_limit_litrs");
                    //System.out.println(limitDay + " " + limitLitrs + " " + usedLimitLitrs);
                } catch (SQLException ex) {
                } finally {
                    try {
                        DB.con.close();
                        if (DB.con.isClosed()) {
                        }
                    } catch (SQLException ex) {
                    }
                }
                ChangePanel.ShowPanel(Pin.EnterPin);
                ChangePanel.ShowPassword(Pin.PinCode);
            }
        }
    }

}
