package ngn.controller;

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
}
