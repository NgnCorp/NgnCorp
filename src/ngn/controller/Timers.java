package ngn.controller;

import java.awt.event.ActionEvent;
import ngn.text.Text;
import ngn.view.Info;
import javax.swing.Timer;
import ngn.view.Card;
import ngn.view.Litrs;
import ngn.view.Pin;
import ngn.view.Wait;

/**
 *
 * @author Офис
 */
public class Timers {

    static Timer errorCardLength;
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

    }

    /*
    public static void errorCardLength() {
        errorCardLength = new javax.swing.Timer(5000, (ActionEvent e) -> {
            if (DB.updateLitrs(newln, code)) {
                DB.writeResult(name, code, leftlitr, sdate); //Записываем операцию в таблицу
                Work.Working.setVisible(false);
                LitrsInput.setText("");
                SchetLitrov.setText("");
                Success();
                GoodBye.setVisible(true);
                GoodBye.requestFocusInWindow();
                InfoMassage.setVisible(false);
                errorCardLength.stop();
                toBeorNottoBe = false;
                Kolonka.StopStartCom3(false);
            }
        });        
        errorCardLength.restart();
    }
     */
    public static void errorPin() {

        Info.ErrorMassage.setText(Text.pin);
        ChangePanel.ShowPanel(Info.InfoMassage);

        errorPin = new Timer(ERRORTIME, (ActionEvent e) -> {
            ChangePanel.ShowPanel(Pin.EnterPin);
            ChangePanel.FocusPassword(Pin.PinCode);
            Pin.PinCode.setText("");
            errorPin.stop();
        });
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

        errorLitrs = new Timer(ERRORTIME, (ActionEvent e) -> {
            ChangePanel.ShowPanel(Litrs.EnterLitrs);
            ChangePanel.FocusLitrsInput(Litrs.LitrsInput);
            Litrs.LitrsInput.setText("");
            errorLitrs.stop();
        });
        errorLitrs.restart();
    }

// One Timer For Open Waiting Panel And Another One For Change Seconds Value <    
    public static void WaitForClient() {
        WaitForClient = new Timer(WAIT_TIME, (ActionEvent e) -> {
            Wait.WaitingSeconds.setText(Text.h1ClickIfUHere);
            ChangePanel.ShowPanel(Wait.Waiting);
            ChangePanel.FocusOff(Litrs.LitrsInput);
            WaitForClient.stop();
            SECONDSVALUE = 15;
            ChangeSecondsValue();
        });
        WaitForClient.restart();
    }

    public static void ChangeSecondsValue() {
        ChangeSecondsValue = new Timer(TIMER_TIME, (ActionEvent e) -> {
            if (SECONDSVALUE <= 0) {
                ChangePanel.ShowPanel(Card.EnterCard);
                ChangePanel.FocusPassword(Card.CardCode);
                ChangePanel.TextOff(Litrs.LitrsInput);
                ChangeSecondsValue.stop();
            } else {
                SECONDSVALUE--;
                Wait.WaitingSeconds.setText(Text.WaitingText + SECONDSVALUE + " секунд.</p>");
            }
        });
        ChangeSecondsValue.restart();
    }
// >

    /*
        ForceMajor = new Timer(600, (ActionEvent e) -> { // Через секунду начало обработки процесса заправки
                        PolozheniePistoleta.setText("НЕ ЗАБУДЬТЕ ПОВЕСИТЬ ПИСТОЛЕТ ПОСЛЕ ЗАПРАВКИ!");
                        SchetLitrov.setText(Kolonka.SchetLitrov);
                        MoneySchetLitrov.setText(Kolonka.MoneySchetLitrov);
                        if (Kolonka.PolozheniePistoleta.equals("ПИСТОЛЕТ ПОВЕШЕН")) { // Ждем повешанья пистолета после заправки
                            // Если что, проблему искать тут. Форс мажор таймер.
                            ForceMajor.stop();
                            LoadingPanel.setVisible(true);
                            if (SchetLitrov.getText().equals("")) { //Исправление бага "моментальное повешанье пистолета"
                                SchetLitrov.setText("0.0");
                            }
                            double litriDouble = Double.valueOf(SchetLitrov.getText());
                            double formatnewln = Double.valueOf(litrnum) - litriDouble;
                            // NEWLN - Разница между литрами на карте и заправленными
                            String leftlitr = String.format(Locale.ENGLISH, "%(.2f", litriDouble);
                            String newln = String.format(Locale.ENGLISH, "%(.2f", formatnewln);
                            // Date 
                            java.util.Date udate = new java.util.Date();
                            Object sdate = new java.sql.Timestamp(udate.getTime());
                            // Записываем операцию в лог 
                            logHistory = "\r\n" + name + "\t" + code + "\t" + leftlitr + "\t" + sdate;
                            try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("C:/NgnApp/log/transactions.txt", true), "cp1251"))) {
                                writer.write(logHistory);
                                writer.flush();
                            } catch (IOException ex) {
                            }
//////////////////////////////////////////KONETS KOLONKI/////////////////////////////////////////////////
                            if (Validate.updateLitrs(newln, code)) { // Записываем в базу новое число литров
                                Validate.writeResult(name, code, leftlitr, sdate);//Записываем операцию в таблицу
                                Working.setVisible(false);
                                LitrsInput.setText("");
                                SchetLitrov.setText("");
                                LoadingPanel.setVisible(false);
                                GoodBye.setVisible(true);
                                GoodBye.requestFocusInWindow();
                                Success();
                                Kolonka.CustomerInfoToZero();
                                CustomerInfoZero(); // обнуляем данные
                            } else if (InternetConn.InternetConn()) {
                                ErrorMassage.setText(Errors.nointernetatstart);
                                InfoMassage.setVisible(true);
                                InfoMassage.requestFocusInWindow();
                                Working.setVisible(false);
                                LoadingPanel.setVisible(false);
                                showSeconds = 30;
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
                                ServerWaiting.restart();
                            } else {
                                noInternetInEnd(newln, code, name, leftlitr, sdate);
                            }
                        }
                    });     
        
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
    
        Success = new Timer(SUCCESSTIME, (ActionEvent e) -> {
            GoodBye.setVisible(false);
            EnterCard.setVisible(true);
            PinCode.setFocusable(false);
            LitrsInput.setFocusable(false);
            CardCode.setFocusable(true);
            CardCode.setText("");
            CardCode.requestFocusInWindow();
            Kolonka.SchetLitrov = "";
            Kolonka.MoneySchetLitrov = "";
            Success.stop();
        });
     */
}
