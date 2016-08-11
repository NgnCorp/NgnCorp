package ngn.controller;

import java.awt.event.ActionEvent;
import java.util.Locale;
import ngn.text.Text;
import javax.swing.Timer;
import ngn.model.DB;
import ngn.view.*;

/**
 *
 * @author Офис
 */
public class Timers {

    static Timer errorCardLength;
    static Timer errorCard;
    static Timer errorPin;
    static Timer errorLitrs;
    static Timer Success;
    static Timer WaitForClient;
    static Timer ChangeSecondsValue;
    static Timer ForceMajor;
    static Timer WaitForServer;
    static Timer TryToConnect;
    static Timer ServerWaiting;
    static Timer KeyPadWorks;
    static Timer KeyPadNotWorks;

    private static final int TIMER_TIME = 1000;
    private static final int ERRORTIME = 3000;
    private static final int SUCCESSTIME = 6000;
    private static final int WAIT_TIME = 30000;
    private static int SECONDSVALUE = 15;

    public Timers() {

        WaitForClient = new Timer(WAIT_TIME, (ActionEvent e) -> {
            Wait.WaitingSeconds.setText(Text.h1ClickIfUHere);
            ChangePanel.ShowPanel(Wait.Waiting);
            ToZero.FocusOff();
            Wait.Waiting.setFocusable(true);
            Wait.Waiting.requestFocusInWindow();
            WaitForClient.stop();
            ChangeSecondsValue();
        });

        errorCard = new Timer(ERRORTIME, (ActionEvent e) -> {
            ChangePanel.ShowPanel(Card.EnterCard);
            ChangePanel.FocusPassword(Card.CardCode);
            Card.CardCode.setText("");
            Info.InfoMassage.setFocusable(false);
            errorCard.stop();
        });

        errorPin = new Timer(ERRORTIME, (ActionEvent e) -> {
            ChangePanel.ShowPanel(Pin.EnterPin);
            ChangePanel.FocusPassword(Pin.PinCode);
            Pin.PinCode.setText("");
            errorPin.stop();
        });

        errorLitrs = new Timer(ERRORTIME, (ActionEvent e) -> {
            ChangePanel.ShowPanel(Litrs.EnterLitrs);
            ChangePanel.FocusLitrsInput();
            ToZero.TextOff();
            errorLitrs.stop();
        });

        ChangeSecondsValue = new Timer(TIMER_TIME, (ActionEvent e) -> {
            if (SECONDSVALUE <= 0) {
                ChangePanel.ShowPanel(Card.EnterCard);
                ChangePanel.FocusPassword(Card.CardCode);
                ToZero.TextOff();
                Wait.Waiting.setFocusable(false);
                ChangeSecondsValue.stop();
            } else {
                SECONDSVALUE--;
                Wait.WaitingSeconds.setText(Text.WaitingText + SECONDSVALUE + " секунд.</p>");
            }
        });

        errorCardLength = new Timer(5000, (ActionEvent e) -> {
            if (DB.updateLitrs(Variables.newln, Variables.code)) {
                DB.writeResult(
                        Variables.name,
                        Variables.code,
                        Variables.leftlitr,
                        Variables.sdate
                ); //Записываем операцию в таблицу
                ChangePanel.ShowPanel(Bye.GoodBye);
                ToZero.TextOff();
                GasStation.StopStartCom3(false);
                Success();
                errorCardLength.stop();
            }
        });

        ForceMajor = new Timer(600, (ActionEvent e) -> { // Через секунду начало обработки процесса заправки
            Work.PolozheniePistoleta.setText("НЕ ЗАБУДЬТЕ ПОВЕСИТЬ ПИСТОЛЕТ ПОСЛЕ ЗАПРАВКИ!");
            Work.SchetLitrov.setText(GasStation.SchetLitrov);
            Work.MoneySchetLitrov.setText(GasStation.MoneySchetLitrov);
            if (GasStation.PolozheniePistoleta.equals("ПИСТОЛЕТ ПОВЕШЕН")) { // Ждем повешанья пистолета после заправки
                // Если что, проблему искать тут. Форс мажор таймер.
                ForceMajor.stop();
                if (Work.SchetLitrov.getText().equals("")) { //Исправление бага "моментальное повешанье пистолета"
                    Work.SchetLitrov.setText("0.0");
                }
                double litriDouble = Double.valueOf(Work.SchetLitrov.getText());
                double formatnewln = Double.valueOf(Litrs.ClientLitrs.getText()) - litriDouble;
                // NEWLN - Разница между литрами на карте и заправленными
                Variables.leftlitr = String.format(Locale.ENGLISH, "%(.2f", litriDouble);
                Variables.newln = String.format(Locale.ENGLISH, "%(.2f", formatnewln);
                // Date 
                java.util.Date udate = new java.util.Date();
                Variables.sdate = new java.sql.Timestamp(udate.getTime());
                /* Записываем операцию в лог 
                logHistory = "\r\n" + name + "\t" + code + "\t" + leftlitr + "\t" + sdate;
                try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:/NgnApp/log/transactions.txt", true), "cp1251"))) {
                    writer.write(logHistory);
                    writer.flush();
                } catch (IOException ex) {
                }
                 */
//////////////////////////////////////////KONETS KOLONKI/////////////////////////////////////////////////
                if (DB.updateLitrs(Variables.newln, Variables.code)) { // Записываем в базу новое число литров
                    DB.writeResult(
                            Variables.name,
                            Variables.code,
                            Variables.leftlitr,
                            Variables.sdate
                    );//Записываем операцию в таблицу
                    ChangePanel.ShowPanel(Bye.GoodBye);
                    Litrs.LitrsInput.setText("");
                    Work.SchetLitrov.setText("");
                    Success();
                    GasStation.CustomerInfoToZero();
                    ToZero.CustomerInfo(); // обнуляем данные
                } else if (InternetConn.InternetConn()) {
                    Info.ErrorMassage.setText(Text.nointernetatstart);
                    ChangePanel.ShowPanel(Info.InfoMassage);
                    ServerWaiting();
                } else {
                    noInternetInEnd(
                            Variables.newln,
                            Variables.code,
                            Variables.name,
                            Variables.leftlitr,
                            Variables.sdate
                    );
                }
            }
        });

        errorCardLength = new Timer(5000, (ActionEvent e) -> {
            if (Variables.litrPlace == 1 && DB.writeResultToBalance(
                    Variables.name,
                    Variables.code,
                    String.format(Locale.ENGLISH, "%(.2f", Variables.newln),
                    Variables.sdate)) {
                ChangePanel.ShowPanel(Bye.GoodBye);
                Litrs.LitrsInput.setText("");
                Work.SchetLitrov.setText("");
                Success();
                GasStation.CustomerInfoToZero();
                ToZero.CustomerInfo(); // обнуляем данные
                errorCardLength.stop();
            } else if (Variables.litrPlace != 1 && DB.updateLitrs(
                    String.format(Locale.ENGLISH, "%(.2f", Variables.newln),
                    Variables.code)) { // Отдаем для записи данные по заправке
                DB.writeResult(
                        Variables.name,
                        Variables.code,
                        Variables.leftlitr,
                        Variables.sdate
                );//Записываем операцию в таблицу
                ChangePanel.ShowPanel(Bye.GoodBye);
                Litrs.LitrsInput.setText("");
                Work.SchetLitrov.setText("");
                Success();
                GasStation.CustomerInfoToZero();
                ToZero.CustomerInfo(); // обнуляем данные
                errorCardLength.stop();
            }
        });

        Success = new Timer(SUCCESSTIME, (ActionEvent e) -> {
            ChangePanel.ShowPanel(Card.EnterCard);
            ChangePanel.FocusPassword(Card.CardCode);
            ToZero.TextOff();
            GasStation.SchetLitrov = "";
            GasStation.MoneySchetLitrov = "";
            Success.stop();
        });

        ServerWaiting = new Timer(1000, (ActionEvent p) -> {
            if (SECONDSVALUE <= 0) {
                ServerWaiting.stop();
                GasStation.StopStartCom3(false);
                noInternetInEnd(
                        Variables.newln,
                        Variables.code,
                        Variables.name,
                        Variables.leftlitr,
                        Variables.sdate
                );
            } else {
                SECONDSVALUE--;
                Info.ErrorMassage.setText(Text.ServerText + SECONDSVALUE + " СЕКУНД.</p>");
                ServerWaiting.restart();
            }
        });
    }

    public static void errorCardLength() {
        errorCardLength.restart();
    }

    public static void errorCard() {
        Info.ErrorMassage.setText(Text.cardvalid);
        ChangePanel.ShowPanel(Info.InfoMassage);
        Info.InfoMassage.setFocusable(true);
        WaitForClient.restart();
        errorCard.restart();
    }

    public static void errorPin() {
        Info.ErrorMassage.setText(Text.pin);
        ChangePanel.ShowPanel(Info.InfoMassage);
        errorPin.restart();
    }

    public static void errorLitrs(String error) {
        switch (error) {
            case "numlitres":
                Info.ErrorMassage.setText(Text.numlitres);
                break;
            case "notenoughlitres":
                Info.ErrorMassage.setText(Text.notenoughlitres);
                break;
            case "getpistol":
                Info.ErrorMassage.setText(Text.getpistol);
                break;
            case "needlitres":
                Info.ErrorMassage.setText(Text.needlitres);
                break;
        }

        WaitForClient.restart();
        ChangePanel.ShowPanel(Info.InfoMassage);
        errorLitrs.restart();
    }

// One Timer For Open Waiting Panel And Another One For Change Seconds Value <    
    public static void WaitForClient() {
        WaitForClient.restart();
    }

    public static void ChangeSecondsValue() {
        SECONDSVALUE = 15;
        ChangeSecondsValue.restart();
    }

    public static void Success() {
        Success.restart();
    }
// >

    public static void ForceMajor() {
        ForceMajor.restart();
    }

    public static void ServerWaiting() {
        SECONDSVALUE = 15;
        ServerWaiting.restart();
    }

    private void noInternetInEnd(String newln, String code, String name, String leftlitr, Object sdate) {
        Info.ErrorMassage.setText(Text.nointernetinend);
        ChangePanel.ShowPanel(Info.InfoMassage);
        GasStation.StopStartCom3(true);
        errorCardLength.restart();
    }
    /*
        WaitForServer = new Timer(1000, (ActionEvent e) -> {
            if (showSeconds <= 0) {
                CardCode.setText("");
                CardCode.setFocusable(true);
                EnterCard.setVisible(true);
                CardCode.requestFocusInWindow();
                InfoMassage.setVisible(false);
                WaitForServer.stop();
                Kolonka.StopStartCom3(false);
                toBeorNottoBe = false;
            } else {
                showSeconds--;
                ErrorMassage.setText("<html><p style=\"text-align:center;\">ОТСУТСТВУЕТ СВЯЗЬ С СЕРВЕРОМ!<br>ЖДЕМ " + showSeconds + " СЕКУНД.</p>");
                WaitForServer.restart();
            }
        });
        
        TryToConnect = new Timer(15000, (ActionEvent e) -> {
                    if (InternetConn.InternetConn()) {
                        LoadingPanel.setVisible(false);
                        Kolonka.StopStartCom3(false);
                        toBeorNottoBe = false;
                        EnterCard.setVisible(true);
                        CardCode.setText("");
                        CardCode.setFocusable(true);
                        CardCode.requestFocusInWindow();
                        TryToConnect.stop();
                    } else {
                        TryToConnect.restart();
                    }
                });
        
        ServerWaiting = new Timer(1000, (ActionEvent p) -> {
                                    if (showSeconds <= 0) {
                                        ServerWaiting.stop();
                                        Kolonka.StopStartCom3(false);
                                        toBeorNottoBe = false;
                                        noInternetInEnd(newln, code, name, leftlitr, sdate);
                                    } else {
                                        showSeconds--;
                                        ErrorMassage.setText("<html><p style=\"text-align:center;\">ОТСУТСТВУЕТ СВЯЗЬ С СЕРВЕРОМ!<br>ЖДЕМ " + showSeconds + " СЕКУНД.</p>");
                                        ServerWaiting.restart();
                                    }
                                });
        
        KyePadWorks = new Timer(1000, (ActionEvent e) -> {
            try {
                if (!klaviaturaCOM4.writeString("00")) {
                    KyePadWorks.stop();
                    KyePadNotWorks = new Timer(1000, (ActionEvent f) -> {
                        try {
                            klaviaturaCOM4 = new SerialPort("COM4");
                            try {
                                klaviaturaCOM4.openPort();
                                klaviaturaCOM4.setParams(2400, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                                klaviaturaCOM4.setEventsMask(SerialPort.MASK_RXCHAR);
                                klaviaturaCOM4.addEventListener(new EventListener());
                            } catch (SerialPortException ex) {
                            }
                            if (klaviaturaCOM4.writeString("00")) {
                                KyePadWorks.restart();
                                KyePadNotWorks.stop();
                            }
                        } catch (SerialPortException ex_kpdw) {
                        }
                    });
        
        KyePadNotWorks = new Timer(1000, (ActionEvent f) -> {
                        try {
                            klaviaturaCOM4 = new SerialPort("COM4");
                            try {
                                klaviaturaCOM4.openPort();
                                klaviaturaCOM4.setParams(2400, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                                klaviaturaCOM4.setEventsMask(SerialPort.MASK_RXCHAR);
                                klaviaturaCOM4.addEventListener(new EventListener());
                            } catch (SerialPortException ex) {
                            }
                            if (klaviaturaCOM4.writeString("00")) {
                                KyePadWorks.restart();
                                KyePadNotWorks.stop();
                            }
                        } catch (SerialPortException ex_kpdw) {
                        }
                    });
     */
}
