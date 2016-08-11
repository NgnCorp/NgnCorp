package ngn.controller;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
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

    static Integer customerId;
    static String pin;
    static String name;
    static String litrnum;
    static String code;
    static Double customerPrice;
    static Double uahBalance;
    static String purse;
    static Integer limitDay;
    static Double limitLitrs;
    static Double usedLimitLitrs;
    static Boolean isLimitClient; // По умолчанию false
    static String limitLitrnum;
    static String newln;
    static String leftlitr;
    static Integer litrPlace;
    static Object sdate;
    static String[] ClientInfo;

    static JPanel[] PanelArray = new JPanel[]{
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
