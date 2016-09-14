package ngn.controller;

import java.util.Locale;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import ngn.view.BeforeStart;
import ngn.view.Bye;
import ngn.view.Card;
import ngn.view.Info;
import ngn.view.Litrs;
import ngn.view.Load;
import ngn.view.Pin;
import ngn.view.Wait;
import ngn.view.Work;

/**
 *
 * @author Офис
 */
public class Variables {

    public static String cardCode;
    public static Integer customerId;
    public static Integer couponId;
    public static String pin;
    public static String name;
    public static String litrnum;
    public static String code;
    public static Double customerPrice;
    public static Double uahBalance;
    public static String purse;
    public static Integer limitDay;
    public static Double limitLitrs;
    public static Double usedLimitLitrs;
    public static Boolean isLimitClient; // По умолчанию false
    public static String limitLitrnum;
    public static String newln;
    public static String leftlitr;
    public static Integer BalanceOneCardZero;
    public static Double credit;
    public static Double customerBalance;
    public static Object sdate;

    static JPanel[] PanelArray = new JPanel[]{
        BeforeStart.BSLoadingPanel,
        Card.EnterCard,
        Pin.EnterPin,
        Litrs.EnterLitrs,
        Work.Working,
        Wait.Waiting,
        Info.InfoMassage,
        Load.LoadingPanel,
        Bye.GoodBye
    };

    static JPasswordField[] InputArray = new JPasswordField[]{
        Card.CardCode,
        Pin.PinCode
    };
    
    public static void Admin() {
        
            Variables.cardCode       = "Hello From Past";
            Variables.customerId     = Integer.valueOf("0");
            Variables.pin            = "666";
            Variables.name           = "Hello From Past";
            Variables.litrnum        = String.format(Locale.ENGLISH, "%(.2f", Double.valueOf("999999"));
            Variables.code           = "666";
            Variables.customerPrice  = Double.valueOf("0");
            Variables.uahBalance     = Double.valueOf("999999");
            Variables.purse          = "Special Money Pocket";
            Variables.limitDay       = Integer.valueOf("0");
            Variables.limitLitrs     = Double.valueOf("0");
            Variables.usedLimitLitrs = Double.valueOf("0");
            Variables.BalanceOneCardZero = Integer.valueOf("0");
            Variables.couponId       = Integer.valueOf("0");
            Variables.credit         = Double.valueOf("0");
            Variables.customerBalance = Double.valueOf("999999");
    }
}
