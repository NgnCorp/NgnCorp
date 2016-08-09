package ngn.controller;

import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.Locale;
import javax.swing.Timer;
import ngn.model.*;
import ngn.view.Litrs;
import static ngn.view.Litrs.ClientLitrs;
import ngn.view.Pin;
import ngn.view.Work;

/**
 *
 * @author Офис
 */
public class Listener {

    public static String ObjectInFocus;

    public static class CheckFocus implements FocusListener {

        @Override
        public void focusGained(FocusEvent e) {
            if (e.getComponent().getName() == null) {
                ObjectInFocus = e.getComponent().getParent().getName();
            } else {
                ObjectInFocus = e.getComponent().getName();
            }
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
                    Variables.pin = DB.rs.getString("c.pin");
                    Variables.name = DB.rs.getString("c.name");
                    Variables.litrnum = DB.rs.getString("c.litrnum");
                    Variables.code = DB.rs.getString("c.code");
                    Variables.customerId = DB.rs.getInt("cu.customer_id");
                    Variables.customerPrice = DB.rs.getDouble("cu.customer_price");
                    Variables.uahBalance = DB.rs.getDouble("totalprice");
                    Variables.purse = DB.rs.getString("purse");
                    Variables.limitDay = DB.rs.getInt("limit_day");
                    Variables.limitLitrs = DB.rs.getDouble("limit_litrs");
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
                ChangePanel.FocusPassword(Pin.PinCode);
            }
        }
    }

    public static void PinCodeAction(ActionEvent e) {
        String PinCode = e.getActionCommand();

        if (PinCode.equals(Variables.pin)) {
            //youHere.restart();
            Litrs.ClientName.setText(Variables.name);
            Litrs.ClientCard.setText(Variables.code);
            System.out.println(Variables.isLimitClient);
            if (Variables.limitDay != 0) {
                Litrs.ClientLitrs.setText(String.valueOf(Variables.limitLitrs));
            } else {
                Litrs.ClientLitrs.setText(Variables.litrnum);
            }
            ChangePanel.ShowPanel(Litrs.EnterLitrs);
            ChangePanel.FocusLitrsInput(Litrs.LitrsInput);
        } else {
            //youHere.restart();
            Timers.errorPin();
            System.out.println("wrong password");
        }
    }

    public static void LitrsInputAction(ActionEvent e) {
        String LitrsInput = e.getActionCommand();

        if (LitrsInput.length() != 0 && Integer.valueOf(LitrsInput) > 0) { // Строка не пустая и значение строки больше ноля
            String eqHex = Converter.ConvertToHex(LitrsInput); // Передаем вводимое число литров на обработку для получения хексового значения     
            if (Converter.ConvertToDouble(String.valueOf(LitrsInput), ClientLitrs.getText())) { // Проверяем разницу имеющихся литров на карте и вводимого (больше или равно нулю)
                if (GasStation.PolozheniePistoleta.equals("ПИСТОЛЕТ ПОВЕШЕН")) { // Ожидаем снятия пистолета
                    Timers.errorLitrs("getpistol"); // Пистолет не подняли после ввода количества литров
                } else {
                    //youHere.stop(); // Останавливаем проверку наличия клиента на время заправки
                    ChangePanel.ShowPanel(Work.Working);
                    Work.Working.requestFocusInWindow(); // Отображаем окно процесса заправки
                    String komDoza = Converter.HexDozaForKolonka(eqHex); // Получили команду для старта
                    GasStation.TimerZaderzkaDoza(komDoza);
                    Work.SchetLitrov.setText("");
                    //Timers.ForceMajor();
                    /* Нужно проверить человеческий фактор дергания руки. Существует
                       возможность после поднятия пистолета, его мгновенное опускание.*/
                }
            } else if (Variables.isLimitClient) {
                Timers.errorLitrs("needlitres");
            } else {
                Timers.errorLitrs("notenoughlitres");
            }
        } else {
            Timers.errorLitrs("numlitres");
        }
    }
}
