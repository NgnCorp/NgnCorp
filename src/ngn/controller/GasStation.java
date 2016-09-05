package ngn.controller;

import Preload.PortCheck;
import java.awt.event.ActionEvent;
import java.util.Locale;
import javax.swing.Timer;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import mail.SendMail;
import ngn.model.DB;

/**
 *
 * @author Офис
 */
public class GasStation {

    private static SerialPort KolonkaCOM3;
    static int komanda;
    static Timer KolonkaStart;
    static Timer ZaderzkaDoza;
    static String PolozheniePistoleta;
    static String SchetLitrov;
    static String MoneySchetLitrov;
    static String OtvetPoDoze;
    static String OtvetKolonki;

    public GasStation() {
        KolonkaCOM3 = new SerialPort(PortCheck.GSPort);
        try {
            KolonkaCOM3.openPort();
            KolonkaCOM3.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_MARK);
            KolonkaCOM3.setEventsMask(SerialPort.MASK_RXCHAR);
            KolonkaCOM3.addEventListener(new EventListener());
            TimerKolonkaStart();
        } catch (SerialPortException ex) {
            //System.out.println(ex);
        }
    }

    public static void TimerZaderzkaDoza(String komDoza) {
        String doza = komDoza;
        ZaderzkaDoza = new javax.swing.Timer(600, (ActionEvent e) -> {
            try {
                ZaderzkaDoza.stop();
                komanda = 1;
                KolonkaCOM3.writeString(doza); // Отправляем на колонку количество литров на отдачу
            } catch (SerialPortException ex) {
                //System.out.println(ex);
            }
        });
        KolonkaStart.stop();
        ZaderzkaDoza.restart();  // Запуск команды "ЗАПРОС СОСТОЯНИЯ"
    }

    public static void TimerKolonkaStart() {
        KolonkaStart = new Timer(600, (ActionEvent e) -> {
            try {
                KolonkaCOM3.writeString("@10510045#");
                komanda = 0;
            } catch (SerialPortException ex) {
                //System.out.println(ex);
            }
        });
        KolonkaStart.restart();  // Запуск команды "ЗАПРОС СОСТОЯНИЯ"
    }

    private class EventListener implements SerialPortEventListener {

        @Override
        public void serialEvent(SerialPortEvent event) {
            if (event.isRXCHAR() && event.getEventValue() != 0) {
                String data = "";
                String oneSymbol;
                try {
                    String StartWork = KolonkaCOM3.readString(1);
                    if (StartWork.indexOf("@") == 0) {
                        // Get data //
                        do {
                            oneSymbol = KolonkaCOM3.readString(1);
                            data += oneSymbol;
                        } while (!"#".equals(oneSymbol));
                        OtvetKolonki = StartWork + data;
                        Integer dataLenghth = OtvetKolonki.length();
                        String dataOffCrx = new String(OtvetKolonki.toCharArray(), 0, dataLenghth - 3);
                        String controlNumber = new String(OtvetKolonki.toCharArray(), dataLenghth - 3, 2);

                        // Check data //
                        int decnum = dataOffCrx.length();
                        char[] decimal = new char[decnum];
                        int crc = 0;
                        for (int col = 0; col < decnum; col++) {
                            decimal[col] = dataOffCrx.charAt(col);
                            crc ^= decimal[col]; // CRC (контрольная сумма)
                        }
                        String pihex = Integer.toHexString(crc);
                        if (pihex.equals(controlNumber)) {
                            //System.out.println(OtvetKolonki);
                        }
                        if (komanda == 0) {
                            switch (OtvetKolonki.indexOf("#")) {
                                case 11:
                                    if (OtvetKolonki.equals("@0151010044#")) {
                                        PolozheniePistoleta = "ПИСТОЛЕТ ПОВЕШЕН";
                                    } else {
                                        PolozheniePistoleta = "ПОВЕСЬТЕ ПИСТОЛЕТ!";
                                    }
                                    break;
                                //String poluchenieOtcheta = KolonkaCOM3.readString(8);
                                //String schetLitrov = proverkaSvyazi + poluchenieOtcheta;
                                ////////////////////////////Читаем 19 байт и проверяем наличие решетки в конце//////////////////////////////////////
                                case 19:
                                    String hexNUM = new String(OtvetKolonki.toCharArray(), 9, 8);
                                    double litrbez = Integer.decode("0x" + hexNUM) / 100.0;
                                    PolozheniePistoleta = "ИДЕТ ПРОЦЕСС ЗАПРАВКИ...";
                                    SchetLitrov = String.valueOf(litrbez);
                                    MoneySchetLitrov = String.format(Locale.ENGLISH, "%(.2f", Variables.customerPrice * litrbez);
                                    break;
                                default:
                                    komanda = 1;
                                    break;
                            }
                        }
                        if (komanda == 1) {
                            try {
                                //OtvetPoDoze = KolonkaCOM3.readString(11);
                                if (OtvetKolonki.equals("@0144010141#")) {
                                    KolonkaCOM3.writeString("@1047010142#"); //PUSK
                                    TimerKolonkaStart();
                                } else {
                                    //ZaderzkaDoza.restart();
                                }
                            } catch (SerialPortException ex) {
                                    SendMail.sendEmail(String.valueOf(ex), "Gas Station error! " + DB.MODULENAME);
                            }
                        }
                    }
                } catch (SerialPortException ex) {
                        SendMail.sendEmail(String.valueOf(ex), "Gas Station error! " + DB.MODULENAME);
                }
            }
        }
    }

    public static void StopStartCom3(boolean value) {
        if (value) {
            KolonkaStart.stop();
        } else {
            KolonkaStart.restart();
        }
    }

    public static void CustomerInfoToZero() {
        SchetLitrov = "";
        MoneySchetLitrov = "";
    }

    public static void KomandaStop() {
        try {
            KolonkaCOM3.writeString("@015801014C#");
        } catch (SerialPortException ex) {
        }
    }
}