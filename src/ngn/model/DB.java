package ngn.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {

    /*
    private static final String URL = "jdbc:mysql://93.73.14.218:3306/ngn";
    private static final String USER = "ValeWar";
    private static final String PASSWORD = "?/09`Yuri_06)Lahno^19Admin#84";
     */
    private static final String URL = "jdbc:mysql://daystar.mysql.ukraine.com.ua:3306/daystar_ngn";
    private static final String USER = "daystar_ngn";
    private static final String PASSWORD = "fky9rpk3";
    private static final String DB_PREFIX = "ngn_";
    public static Connection con;
    public static ResultSet rs;
    protected static boolean conStatus = true;

    /* Values of MODULENAME */
    //Каланчак Пионерская
    //Чаплынка Кудри
    //Полтава Половка
    //Новая Каховка
    //Чернигов Карпиловка
    //Подгородье
    //Конотоп
    //Павлоград
    private static final String DESCRIPTION = "Заправка на АЗС";
    private static final String MODULENAME = GetModuleName(); // ГЕОЛОКАЦИЯ !!!
    public static StringBuilder allText;
    public static final String PATH= "С:\\NgnUpdater\\ModuleName.txt";
    
    
    public static boolean cardCode(String cardcode) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pst = con.prepareStatement(
                    "SELECT cu.customer_id, cu.credit, c.name, c.litr_place, c.litrnum, c.code, c.pin, cu.customer_price, cu.customer_price*c.litrnum AS totalprice, cfvd.name AS purse, ("
                    + "SELECT cl.text FROM " + DB_PREFIX + "coupon_limit cl WHERE cl.limit_id=12 AND cl.coupon_id=c.coupon_id) AS limit_day, ("
                    + "SELECT cl.text FROM " + DB_PREFIX + "coupon_limit cl WHERE cl.limit_id=13 AND cl.coupon_id=c.coupon_id) AS limit_litrs, ("
                    + "SELECT SUM(ch.leftlitrs) FROM " + DB_PREFIX + "cards_history ch WHERE ch.code=c.code AND DATE(ch.date) BETWEEN DATE(CURDATE()) AND DATE(CURDATE() + INTERVAL limit_day DAY)) AS used_limit_litrs, ("
                    + "SELECT SUM(cr.points) FROM " + DB_PREFIX + "customer_reward cr WHERE cr.customer_id=cu.customer_id) AS customer_balance FROM " + DB_PREFIX + "coupon c "
                    + "LEFT JOIN " + DB_PREFIX + "coupon_customer cc ON c.coupon_id=cc.coupon_id "
                    + "LEFT JOIN " + DB_PREFIX + "customer cu ON cc.customer_id=cu.customer_id "
                    + "LEFT JOIN " + DB_PREFIX + "custom_field_value_description cfvd ON cfvd.custom_field_value_id=SUBSTRING(cu.custom_field,7,1) "
                    + "WHERE c.cardcode=?");
            pst.setString(1, cardcode);
            rs = pst.executeQuery();
            conStatus = true;
            return rs.next();
        } catch (ClassNotFoundException | SQLException e) {
            conStatus = false;
            return false;
        }
    }
    public static String GetModuleName() {
        try (BufferedReader br = new BufferedReader(new FileReader(PATH))) {
            // чтение посимвольно
            allText = new StringBuilder();
            char[] buff = new char[1];
            int c;
            while ((c = br.read(buff)) != -1) {
                allText.append(buff, 0, c);
            }
            return String.valueOf(allText);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return "";
        }
    }

    public static boolean updateLitrs(String newln, String cardnum) {
        try {
            Double checkLitr = 0.00;
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement check = con.prepareStatement("SELECT litrnum FROM " + DB_PREFIX + "coupon WHERE code=?");
            check.setString(1, cardnum);
            rs = check.executeQuery();
            if (rs.next()) {
                checkLitr = rs.getDouble("litrnum");
                checkLitr -= Double.valueOf(newln);
                System.out.println(checkLitr);
                PreparedStatement pst = con.prepareStatement("UPDATE " + DB_PREFIX + "coupon set litrnum=? WHERE code=?");
                pst.setString(1, String.valueOf(checkLitr));
                pst.setString(2, cardnum);
                pst.executeUpdate();
                con.setAutoCommit(true);
                conStatus = true;
            } else {
                PreparedStatement pst = con.prepareStatement("UPDATE " + DB_PREFIX + "coupon set litrnum=? WHERE code=?");
                pst.setString(1, newln);
                pst.setString(2, cardnum);
                pst.executeUpdate();
                con.setAutoCommit(true);
                conStatus = true;                
            }
        } catch (ClassNotFoundException | SQLException e) {
            conStatus = false;
            return false;
        }
        return true;
    }

    public static boolean writeResult(String name, String code, String leftlitrs, Object sdate) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pst = con.prepareStatement("INSERT INTO " + DB_PREFIX + "cards_history (name, code, leftlitrs, modulename, description) VALUES (?, ?, ?, ?, ?)");
            pst.setString(1, name);
            pst.setString(2, code);
            pst.setString(3, leftlitrs);
            pst.setString(4, MODULENAME);
            pst.setString(5, DESCRIPTION);
            PreparedStatement pstBallance = con.prepareStatement("INSERT INTO " + DB_PREFIX + "customer_reward (customer_id, litrsoff, description, comment_m, date_added) VALUES (?, ?, ?, ?, ?)");
            //pstBallance.setString(1, Integer.toString(NgnApp.customerId));
            pstBallance.setString(2, "-" + leftlitrs);
            pstBallance.setString(3, DESCRIPTION + " " + MODULENAME + ". Карта: " + code + " " + name);
            pstBallance.setString(4, DESCRIPTION + " " + MODULENAME + ". Карта: " + code + " " + name);
            pstBallance.setObject(5, sdate);
            pst.executeUpdate();
            pstBallance.executeUpdate();
            con.setAutoCommit(true);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public static boolean writeResultToBalance(String name, String code, String leftlitrs, Object sdate) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pst = con.prepareStatement("INSERT INTO " + DB_PREFIX + "cards_history (name, code, leftlitrs, modulename, description) VALUES (?, ?, ?, ?, ?)");
            pst.setString(1, name);
            pst.setString(2, code);
            pst.setString(3, leftlitrs);
            pst.setString(4, MODULENAME);
            pst.setString(5, DESCRIPTION);
            PreparedStatement pstBallance = con.prepareStatement("INSERT INTO " + DB_PREFIX + "customer_reward (customer_id, points, description, comment_m, date_added) VALUES (?, ?, ?, ?, ?)");
            //pstBallance.setString(1, Integer.toString(NgnApp.customerId));
            pstBallance.setString(2, "-" + leftlitrs);
            pstBallance.setString(3, DESCRIPTION + " " + MODULENAME + ". Карта: " + code + " " + name);
            pstBallance.setString(4, DESCRIPTION + " " + MODULENAME + ". Карта: " + code + " " + name);
            pstBallance.setObject(5, sdate);
            pst.executeUpdate();
            pstBallance.executeUpdate();
            con.setAutoCommit(true);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
}
