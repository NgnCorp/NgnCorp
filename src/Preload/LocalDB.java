package Preload;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import ngn.controller.ReadWI;
import ngn.controller.Variables;
import ngn.controller.WriteWI;
import ngn.text.Config;
import ngn.text.Text;
import static ngn.view.BeforeStart.BSLoadingText;

/**
 *
 * @author Валерик
 */
public class LocalDB {

    private static final String URL = Config.DB_URL;
    private static final String USER = Config.DB_USER;
    private static final String PASSWORD = Config.DB_PASS;
    private static final String DB_PREFIX = Config.DB_PREFIX;
    public static Connection conLDB;
    public static ResultSet rsLDB;

    public static void LocalDB() {
        BSLoadingText.setText(Text.createLDB);
        WriteWI.LDBToZero();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conLDB = DriverManager.getConnection(URL, USER, PASSWORD);
            //System.out.println("URL: " + URL + " User: " + USER + " PASS: " + PASSWORD);
            PreparedStatement pst = conLDB.prepareStatement(
                    "SELECT c.coupon_id, c.cardcode, cu.customer_id, cu.credit, c.name, c.litr_place, c.litrnum, c.code, c.pin, cu.customer_price, cu.customer_price*c.litrnum AS totalprice, cfvd.name AS purse, ("
                    + "SELECT cl.text FROM " + DB_PREFIX + "coupon_limit cl WHERE cl.limit_id=12 AND cl.coupon_id=c.coupon_id) AS limit_day, ("
                    + "SELECT cl.text FROM " + DB_PREFIX + "coupon_limit cl WHERE cl.limit_id=13 AND cl.coupon_id=c.coupon_id) AS limit_litrs, ("
                    + "SELECT SUM(ch.leftlitrs) FROM " + DB_PREFIX + "cards_history ch WHERE ch.code=c.code AND DATE(ch.date) BETWEEN DATE(CURDATE()) AND DATE(CURDATE() + INTERVAL limit_day DAY)) AS used_limit_litrs, ("
                    + "SELECT SUM(cr.points)+cu.credit FROM " + DB_PREFIX + "customer_reward cr WHERE cr.customer_id=cu.customer_id) AS customer_balance FROM " + DB_PREFIX + "coupon c "
                    + "LEFT JOIN " + DB_PREFIX + "coupon_customer cc ON c.coupon_id=cc.coupon_id "
                    + "LEFT JOIN " + DB_PREFIX + "customer cu ON cc.customer_id=cu.customer_id "
                    + "LEFT JOIN " + DB_PREFIX + "custom_field_value_description cfvd ON cfvd.custom_field_value_id=SUBSTRING(cu.custom_field,7,1)");
            rsLDB = pst.executeQuery();
            while (rsLDB.next()) {
                String[] LocalClientInfo = new String[]{
                    rsLDB.getString("c.cardcode"),
                    rsLDB.getString("cu.customer_id"),
                    rsLDB.getString("c.pin"),
                    rsLDB.getString("c.name"),
                    rsLDB.getString("c.litrnum"),
                    rsLDB.getString("c.code"),
                    String.valueOf(rsLDB.getDouble("cu.customer_price")),
                    String.valueOf(rsLDB.getDouble("totalprice")),
                    rsLDB.getString("purse"),
                    String.valueOf(rsLDB.getInt("limit_day")),
                    String.valueOf(rsLDB.getInt("limit_litrs")),
                    String.valueOf(rsLDB.getDouble("used_limit_litrs")),
                    String.valueOf(rsLDB.getInt("c.litr_place")),
                    String.valueOf(rsLDB.getInt("c.coupon_id")),
                    String.valueOf(rsLDB.getString("cu.credit")),
                    String.valueOf(rsLDB.getDouble("customer_balance"))
                };
                WriteWI.Write(LocalClientInfo, WriteWI.PATHLDB, true);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        } finally {
            try {
                conLDB.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        BSLoadingText.setText(Text.LDBdone);
    }

    public static void WriteToLocalDB() {
        String[] UpdateVariables = new String[3];
        if (Variables.isLimitClient) { // Есть ли лимиты по клиенту?
            if (Variables.BalanceOneCardZero == 1) { // 1 - баланс клиента 0 - баланс карты
                UpdateVariables[0] = String.valueOf(Variables.litrnum);//litrnum
                UpdateVariables[1] = String.valueOf(Variables.usedLimitLitrs) + Double.valueOf(Variables.leftlitr);//usedLimitLitrs
                UpdateVariables[2] = String.valueOf(Variables.customerBalance - Double.valueOf(Variables.leftlitr));//customerBalance
            } else {
                UpdateVariables[0] = String.valueOf(Variables.litrnum);//litrnum;
                UpdateVariables[1] = String.valueOf(Variables.usedLimitLitrs) + Double.valueOf(Variables.leftlitr);//usedLimitLitrs
                UpdateVariables[2] = String.valueOf(Variables.customerBalance - Double.valueOf(Variables.leftlitr));//customerBalance
            }
        } else if (Variables.BalanceOneCardZero == 1) {
            UpdateVariables[0] = Variables.newln;//litrnum
            UpdateVariables[1] = Variables.leftlitr;//usedLimitLitrs
            UpdateVariables[2] = String.valueOf(Variables.customerBalance - Double.valueOf(Variables.leftlitr));//customerBalance
        } else {
            UpdateVariables[0] = Variables.newln;//litrnum
            UpdateVariables[1] = Variables.leftlitr;//usedLimitLitrs
            UpdateVariables[2] = String.valueOf(Variables.customerBalance - Double.valueOf(Variables.leftlitr));//customerBalance
        }
        System.out.println(Arrays.toString(UpdateVariables));
        ReadWI.ReWrite(Variables.cardCode, UpdateVariables);
    }
}
