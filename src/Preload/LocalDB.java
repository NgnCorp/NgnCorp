/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Preload;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import ngn.controller.WriteWI;
import static ngn.model.DB.con;
import static ngn.model.DB.rs;

/**
 *
 * @author Валерик
 */
public class LocalDB {

    /*
    private static final String URL = "jdbc:mysql://93.73.14.218:3306/ngn";
    private static final String USER = "ValeWar";
    private static final String PASSWORD = "?/09`Yuri_06)Lahno^19Admin#84";
     */
    private static final String URL = "jdbc:mysql://daystar.mysql.ukraine.com.ua:3306/daystar_ngn";
    private static final String USER = "daystar_ngn";
    private static final String PASSWORD = "fky9rpk3";
    private static final String DB_PREFIX = "ngn_";

    public static void LocalDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pst = con.prepareStatement(
                    "SELECT c.cardcode, cu.credit, c.name, c.litr_place, c.litrnum, c.code, c.pin, cu.customer_price, cu.customer_price*c.litrnum AS totalprice, cfvd.name AS purse, ("
                    + "SELECT cl.text FROM " + DB_PREFIX + "coupon_limit cl WHERE cl.limit_id=12 AND cl.coupon_id=c.coupon_id) AS limit_day, ("
                    + "SELECT cl.text FROM " + DB_PREFIX + "coupon_limit cl WHERE cl.limit_id=13 AND cl.coupon_id=c.coupon_id) AS limit_litrs, ("
                    + "SELECT SUM(ch.leftlitrs) FROM " + DB_PREFIX + "cards_history ch WHERE ch.code=c.code AND DATE(ch.date) BETWEEN DATE(CURDATE()) AND DATE(CURDATE() + INTERVAL limit_day DAY)) AS used_limit_litrs, ("
                    + "SELECT SUM(cr.points) FROM " + DB_PREFIX + "customer_reward cr WHERE cr.customer_id=cu.customer_id) AS customer_balance FROM " + DB_PREFIX + "coupon c "
                    + "LEFT JOIN " + DB_PREFIX + "coupon_customer cc ON c.coupon_id=cc.coupon_id "
                    + "LEFT JOIN " + DB_PREFIX + "customer cu ON cc.customer_id=cu.customer_id "
                    + "LEFT JOIN " + DB_PREFIX + "custom_field_value_description cfvd ON cfvd.custom_field_value_id=SUBSTRING(cu.custom_field,7,1)");
            rs = pst.executeQuery();
            while(rs.next()) {
                    String[] LocalClientInfo = new String[]{
                        rs.getString("c.name"),
                        rs.getString("c.litrnum"),
                        rs.getString("c.code"),
                        String.valueOf(rs.getDouble("cu.customer_price")),
                        String.valueOf(rs.getDouble("totalprice")),
                        rs.getString("purse"),
                        String.valueOf(rs.getInt("limit_day")),
                        String.valueOf(rs.getInt("limit_litrs")),
                        String.valueOf(rs.getDouble("used_limit_litrs"))
                    };
                    WriteWI.WriteLDB(LocalClientInfo);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }
}