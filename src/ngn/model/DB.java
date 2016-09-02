package ngn.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ngn.text.Config;
import ngn.text.Paths;

public class DB {

    private static final String URL = Config.DB_URL;
    private static final String USER = Config.DB_USER;
    private static final String PASSWORD = Config.DB_PASS;
    private static final String DB_PREFIX = Config.DB_PREFIX;
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
    private static final String MODULENAME = GetModuleName();
    public static StringBuilder allText;
    public static int data;
    public static final String PATH = Paths.MODULENAMEPATH;
    public static String[] TransInfo;

    public static String GetModuleName() {
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(PATH), "windows-1251")) {
            // чтение посимвольно
            data = isr.read();
            allText = new StringBuilder(data);
            while (data != -1) {
                allText.append((char) data);
                data = isr.read();
            }
            return String.valueOf(allText);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return "";
        }
    }

    /*
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
     */
    public static boolean SendTransactionsToDB(String[] Transactions) {
        int transactionsnum = 0;
        boolean ClientTypeBalanceExist = false;
        boolean ClientTypeCardBalanceExist = false;

        String sqlCardsHistory = "INSERT INTO `" + DB_PREFIX + "cards_history` (name, code, leftlitrs, modulename, description, date) VALUES ";
        String sqlCustomerReward = "INSERT INTO `" + DB_PREFIX + "customer_reward` (customer_id, points, description, comment_m, date_added) VALUES ";
        String sqlCouponSelect = "SELECT litrnum FROM `" + DB_PREFIX + "coupon` WHERE code IN ";
        String sqlCouponInsert = "INSERT INTO `" + DB_PREFIX + "coupon` (coupon_id, litrnum) VALUES ";
        for (String custTrans : Transactions) {

            TransInfo = custTrans.split("=>");
            String ClientType = TransInfo[0]; //1 = balance, 0 = card balance
            String ClientId = TransInfo[1];
            String ClientNewLitrs = TransInfo[2];
            String ClientName = TransInfo[3];
            String ClientCardCode = TransInfo[4];
            String ClientLeftLitrs = TransInfo[5];
            String TransactionDate = TransInfo[6];
            String ClientCardId = TransInfo[7];

            if ("1".equals(ClientType)) { // Balance
                sqlCardsHistory += "('" + ClientName + "','" + ClientCardCode + "','" + ClientLeftLitrs + "','" + MODULENAME + "','" + DESCRIPTION + "','" + TransactionDate + "')";
                sqlCustomerReward += "('" + ClientId + "','-" + ClientLeftLitrs + "','" + DESCRIPTION + " " + MODULENAME + ". Карта: " + ClientCardCode + " " + ClientName + "','" + TransactionDate + "')";
                if (transactionsnum < Transactions.length) {
                    sqlCardsHistory += ",";
                    sqlCustomerReward += ",";
                }
                ClientTypeBalanceExist = true;
            }

            if ("0".equals(ClientType)) { // CardBalance
                sqlCouponSelect += "('" + ClientCardCode + "')";
                sqlCouponInsert += "('" + ClientCardId + "','" + ClientNewLitrs + "')";
                if (transactionsnum < Transactions.length) {
                    sqlCouponSelect += ",";
                    sqlCouponInsert += ",";
                }
                ClientTypeCardBalanceExist = true;
                sqlCouponInsert += " ON DUPLICATE KEY UPDATE `litrnum` = VALUES(`litrnum`)";
            }
            transactionsnum++;
        }
        System.out.println(sqlCardsHistory + "\n" + sqlCustomerReward + "\n" + sqlCouponSelect + "\n" + sqlCouponInsert + "\nБаланс: " + ClientTypeBalanceExist + "\nКарта: " + ClientTypeCardBalanceExist);
        /*
        if (ClientTypeBalanceExist) {
            if (!ClientTypeBalance(sqlCardsHistory, sqlCustomerReward)) {
                return false;
            }
        }
        if (ClientTypeCardBalanceExist) {
            if (!ClientTypeCardBalance(sqlCouponSelect, sqlCouponInsert)) {
                return false;
            }
        }
         */
        return true;
    }

    public static boolean ClientTypeBalance(String QueryCardsHistory, String QueryCustomerReward) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pst1 = con.prepareStatement(QueryCardsHistory);
            PreparedStatement pst2 = con.prepareStatement(QueryCustomerReward);
            pst1.executeUpdate();
            pst2.executeUpdate();
            con.setAutoCommit(true);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public static boolean ClientTypeCardBalance(String QueryCouponSelect, String QueryCouponInsert) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pst3 = con.prepareStatement(QueryCouponSelect);
            rs = pst3.executeQuery();
            if (rs.next()) {
                Double checkLitr = rs.getDouble("litrnum");// - Double.valueOf(Query);
                PreparedStatement pst = con.prepareStatement("UPDATE " + DB_PREFIX + "coupon set litrnum=? WHERE code=?");

                pst.executeUpdate();
                con.setAutoCommit(true);
                conStatus = true;
            } else {
                System.out.println("THIS IS WHY WE NEED TO DO DOUBLE UPDATE");
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
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
            /*
            PreparedStatement pstBallance = con.prepareStatement("INSERT INTO " + DB_PREFIX + "customer_reward (customer_id, litrsoff, description, comment_m, date_added) VALUES (?, ?, ?, ?, ?)");
            pstBallance.setString(1, Integer.toString(NgnApp.customerId));
            pstBallance.setString(2, "-" + leftlitrs);
            pstBallance.setString(3, DESCRIPTION + " " + MODULENAME + ". Карта: " + code + " " + name);
            pstBallance.setString(4, DESCRIPTION + " " + MODULENAME + ". Карта: " + code + " " + name);
            pstBallance.setObject(5, sdate);
             */
            pst.executeUpdate();
            //pstBallance.executeUpdate();
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
