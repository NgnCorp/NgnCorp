package ngn.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import mail.SendMail;
import ngn.text.Config;
import ngn.text.Paths;
import ngn.text.Text;

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
    public static final String MODULENAME = GetModuleName();
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

    public static boolean SendTransactionsToDB(String[] Transactions) {
        int transactionsnum = 1;
        boolean ClientTypeCardBalanceExist = false;

        String sqlCardsHistory = "INSERT INTO `" + DB_PREFIX + "cards_history` (name, code, leftlitrs, modulename, description, date) VALUES ";
        String sqlCustomerReward = "INSERT INTO `" + DB_PREFIX + "customer_reward` (customer_id, points, litrsoff, description, comment_m, date_added) VALUES ";
        String sqlCouponInsert = "INSERT IGNORE INTO `" + DB_PREFIX + "coupon` (coupon_id, litrnum) VALUES ";
        for (String custTrans : Transactions) {

            TransInfo = custTrans.split("=>");
            if (TransInfo.length == 7) {
                String ClientType = TransInfo[0]; //1 = balance, 0 = card balance
                String ClientId = TransInfo[1];
                String ClientName = TransInfo[2];
                String ClientCardCode = TransInfo[3];
                String ClientLeftLitrs = TransInfo[4];
                String TransactionDate = TransInfo[5];
                String ClientCardId = TransInfo[6];
                if (ClientType.contains("1")) { // Balance
                    sqlCardsHistory += "('" + ClientName + "','" + ClientCardCode + "','" + ClientLeftLitrs + "','" + MODULENAME + "','" + DESCRIPTION + "','" + TransactionDate + "')";
                    sqlCustomerReward += "('" + ClientId + "','-" + ClientLeftLitrs + "',DEFAULT,'" + DESCRIPTION + " " + MODULENAME + ". Карта: " + ClientCardCode + " " + ClientName + "','" + DESCRIPTION + " " + MODULENAME + ". Карта: " + ClientCardCode + " " + ClientName + "','" + TransactionDate + "')";
                }

                if (ClientType.contains("0")) { // CardBalance
                    sqlCardsHistory += "('" + ClientName + "','" + ClientCardCode + "','" + ClientLeftLitrs + "','" + MODULENAME + "','" + DESCRIPTION + "','" + TransactionDate + "')";
                    sqlCustomerReward += "('" + ClientId + "',DEFAULT,'-" + ClientLeftLitrs + "','" + DESCRIPTION + " " + MODULENAME + ". Карта: " + ClientCardCode + " " + ClientName + "','" + DESCRIPTION + " " + MODULENAME + ". Карта: " + ClientCardCode + " " + ClientName + "','" + TransactionDate + "')";
                    sqlCouponInsert += "('" + ClientCardId + "','" + ClientLeftLitrs + "')";
                    ClientTypeCardBalanceExist = true;
                    if (transactionsnum < Transactions.length) {
                        sqlCouponInsert += ",";
                    }
                }
                if (transactionsnum < Transactions.length) {
                    sqlCardsHistory += ",";
                    sqlCustomerReward += ",";
                } else if (sqlCouponInsert.substring(sqlCouponInsert.length() - 1).equals(",")) {
                    sqlCouponInsert = sqlCouponInsert.substring(0, sqlCouponInsert.length() - 1) + " ON DUPLICATE KEY UPDATE `litrnum` = `litrnum` - VALUES(`litrnum`)";
                } else {
                    sqlCouponInsert += " ON DUPLICATE KEY UPDATE `litrnum` = `litrnum` - VALUES(`litrnum`)";
                }
                transactionsnum++;
            }
        }
        System.out.println(sqlCardsHistory + "\n" + sqlCustomerReward + "\n" + sqlCouponInsert);

        if (!ClientTypeBalance(sqlCardsHistory, sqlCustomerReward)) {
            return false;
        }
        if (ClientTypeCardBalanceExist) {
            if (!ClientTypeCardBalance(sqlCouponInsert)) {
                return false;
            }
        }
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
            SendMail.sendEmail(String.valueOf(e), Text.cannotConSer + " " + DB.MODULENAME);
            return false;
        }
        return true;
    }

    public static boolean ClientTypeCardBalance(String QueryCouponInsert) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement pst3 = con.prepareStatement(QueryCouponInsert);
            pst3.executeUpdate();
            con.setAutoCommit(true);
        } catch (ClassNotFoundException | SQLException e) {
            SendMail.sendEmail(String.valueOf(e), Text.cannotConSer + " " + DB.MODULENAME);
            return false;
        }
        return true;
    }
}
