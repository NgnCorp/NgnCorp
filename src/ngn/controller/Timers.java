package ngn.controller;

import java.awt.event.ActionEvent;
import ngn.text.Text;
import ngn.view.Info;
import javax.swing.Timer;
import ngn.view.Pin;

/**
 *
 * @author Офис
 */
public class Timers {

    Timer errorCardLength;
    static Timer errorPin;
    Timer errorLitrs;
    Timer Success;
    Timer youHere;
    Timer timerText;
    Timer ForceMajor;
    Timer WaitForServer;
    Timer TryToConnect;
    Timer ServerWaiting;
    Timer KeyPadWorks;
    Timer KeyPadNotWorks;

    private static final int TIMER_TIME = 1000;
    private static final int ERRORTIME = 3000;
    private static final int SUCCESSTIME = 6000;
    private static final int LAST_TIME = 30000;

    public Timers() {
        
    }
        /*
    public static void errorCardLength()
        errorCardLength = new javax.swing.Timer(5000, (ActionEvent e) -> {
            if (Validate.updateLitrs(newln, code)) {
                Validate.writeResult(name, code, leftlitr, sdate); //Записываем операцию в таблицу
                Working.setVisible(false);
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
        */
    public static void errorPin() {
        
        Info.ErrorMassage.setText(Text.pin);
        ChangePanel.ShowPanel(Info.InfoMassage);
        
        errorPin = new Timer(ERRORTIME, (ActionEvent e) -> {
            Info.InfoMassage.setVisible(false);
            ChangePanel.ShowPanel(Pin.EnterPin);
            ChangePanel.FocusPassword(Pin.PinCode);
            Pin.PinCode.setText("");
            errorPin.stop();
        });
        errorPin.restart();
    }
        /*
        errorLitrs = new Timer(ERRORTIME, (ActionEvent e) -> {
            InfoMassage.setVisible(false);
            EnterLitrs.setVisible(true);
            LitrsInput.setFocusable(true);
            LitrsInput.requestFocusInWindow();
            errorLitrs.stop();
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
        
        youHere = new Timer(LAST_TIME, (ActionEvent e) -> {
            endTimer.setText("<html><p style=\"text-align:center;\">Нажмите любую кнопку на клавиатуре.<br>Осталось: 10 секунд.</p>");
            Waiting.setVisible(true);
            Waiting.requestFocusInWindow();
            PinCode.setFocusable(false);
            LitrsInput.setFocusable(false);
            CardCode.setFocusable(false);
            EnterCard.setVisible(false);
            EnterPin.setVisible(false);
            EnterLitrs.setVisible(false);
            youHere.stop();
            value = 10;
            timerText.restart();
            SwingUtilities.getRootPane(btnYes).setDefaultButton(btnYes);
        });
        
        timerText = new Timer(TIMER_TIME, (ActionEvent e) -> {
            if (value <= 0) {
                Waiting.setVisible(false);
                EnterPin.setVisible(false);
                EnterLitrs.setVisible(false);
                LitrsInput.setText("");
                PinCode.setText("");
                CardCode.setText("");
                PinCode.setFocusable(false);
                LitrsInput.setFocusable(false);
                CardCode.setFocusable(true);
                EnterCard.setVisible(true);
                CardCode.requestFocusInWindow();
                timerText.stop();
                SwingUtilities.getRootPane(btnYes).setDefaultButton(null);
            } else {
                value--;
                endTimer.setText("<html><p style=\"text-align:center;\">Нажмите любую кнопку на клавиатуре.<br>Осталось: " + value + " секунд.</p>");
            }
        });
        
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
         */
}
