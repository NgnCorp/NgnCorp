package ngn.controller;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.Arrays;
import static ngn.controller.ReadWI.customerInfo;
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

    public static void CardCodeAction(ActionEvent e) {
        String CardCode = Converter.DeleteSymbols(e.getActionCommand());

        if (CardCode.length() == 10) {
            ReadWI.FindCardName(CardCode);
            if (DB.cardCode(CardCode)) {
                try {
                    Variables.pin = ReadWI.personalInfo[2];//DB.rs.getString("c.pin");
                    Variables.name = ReadWI.personalInfo[3];//DB.rs.getString("c.name");
                    Variables.litrnum = ReadWI.personalInfo[4];//DB.rs.getString("c.litrnum");
                    Variables.code = ReadWI.personalInfo[5];//DB.rs.getString("c.code");
                    Variables.customerId = Integer.valueOf(ReadWI.personalInfo[2]);//DB.rs.getInt("cu.customer_id");
                    Variables.customerPrice = Double.valueOf(ReadWI.personalInfo[6]);//DB.rs.getDouble("cu.customer_price");
                    Variables.uahBalance = Double.valueOf(ReadWI.personalInfo[7]);//DB.rs.getDouble("totalprice");
                    Variables.purse = ReadWI.personalInfo[8];//DB.rs.getString("purse");
                    Variables.limitDay = Integer.valueOf(ReadWI.personalInfo[9]);//DB.rs.getInt("limit_day");
                    Variables.limitLitrs = Double.valueOf(ReadWI.personalInfo[10]);//DB.rs.getDouble("limit_litrs");
                    Variables.usedLimitLitrs = Double.valueOf(ReadWI.personalInfo[11]);//DB.rs.getDouble("used_limit_litrs");
                    Variables.litrPlace = Integer.valueOf(ReadWI.personalInfo[12]);//DB.rs.getInt("litr_place");

                    Variables.ClientInfo = new String[]{
                        String.valueOf(Variables.customerId),
                        Variables.name,
                        Variables.litrnum,
                        Variables.code,
                        String.valueOf(Variables.customerPrice),
                        String.valueOf(Variables.uahBalance),
                        Variables.purse,
                        String.valueOf(Variables.limitDay),
                        String.valueOf(Variables.limitLitrs),
                        String.valueOf(Variables.usedLimitLitrs)
                    };
                    WriteWI.WriteWI(Variables.ClientInfo);
                    /*
                    System.out.println(
                            "customerId: " + Variables.customerId
                            + " pin: " + Variables.pin
                            + " name: " + Variables.name
                            + " litrnum: " + Variables.litrnum
                            + " code: " + Variables.code
                            + " customerPrice: " + Variables.customerPrice
                            + " UAHBalance: " + Variables.uahBalance
                            + " purse: " + Variables.purse
                            + " limitDay: " + Variables.limitDay
                            + " limitLitrs: " + Variables.limitLitrs
                            + " usedLimitLitrs: " + Variables.usedLimitLitrs
                    );
                     */
                } catch (SQLException ex) {
                } finally {
                    try {
                        DB.con.close();
                        if (DB.con.isClosed()) {
                        }
                    } catch (SQLException ex) {
                    }
                }
                Converter.chekLimit();
                ChangePanel.ShowPanel(Pin.EnterPin);
                ChangePanel.FocusPassword(Pin.PinCode);
                Timers.WaitForClient();
            }
        } else {
            Timers.errorCard();
        }
    }

    public static void PinCodeAction(ActionEvent e) {
        String PinCode = Converter.DeleteSymbols(e.getActionCommand());

        if (PinCode.equals(Variables.pin)) {
            Litrs.ClientName.setText(Variables.name);
            Litrs.ClientCard.setText(Variables.code);
            System.out.println(Variables.isLimitClient);
            if (Variables.isLimitClient) {
                Litrs.ClientLitrs.setText(String.valueOf(Variables.limitLitrs));
            } else {
                Litrs.ClientLitrs.setText(Variables.litrnum);
            }
            ChangePanel.ShowPanel(Litrs.EnterLitrs);
            ChangePanel.FocusLitrsInput();
        } else {
            Timers.WaitForClient();
            Timers.errorPin();
        }
    }

    public static void LitrsInputAction(ActionEvent e) {
        String LitrsInput = Converter.DeleteSymbols(e.getActionCommand());

        if (LitrsInput.length() != 0 && Integer.valueOf(LitrsInput) > 0) { // Строка не пустая и значение строки больше ноля
            String eqHex = Converter.ConvertToHex(LitrsInput); // Передаем вводимое число литров на обработку для получения хексового значения     
            if (Converter.ConvertToDouble(LitrsInput, ClientLitrs.getText())) { // Проверяем разницу имеющихся литров на карте и вводимого (больше или равно нулю)
                if (GasStation.PolozheniePistoleta.equals("ПИСТОЛЕТ ПОВЕШЕН")) { // Ожидаем снятия пистолета
                    Timers.errorLitrs("getpistol"); // Пистолет не подняли после ввода количества литров
                } else {
                    Timers.WaitForClient.stop(); // Останавливаем проверку наличия клиента на время заправки
                    ChangePanel.ShowPanel(Work.Working);
                    Work.Working.requestFocusInWindow(); // Отображаем окно процесса заправки
                    String komDoza = Converter.HexDozaForKolonka(eqHex); // Получили команду для старта
                    GasStation.TimerZaderzkaDoza(komDoza);
                    Work.SchetLitrov.setText("");
                    Timers.ForceMajor();
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
