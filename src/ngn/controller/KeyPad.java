package ngn.controller;

import static Preload.BackendTimers.KyePadWorks;
import Preload.PortCheck;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import ngn.Ngn;
import static ngn.controller.ChangePanel.CheckVisibility;
import static ngn.controller.Timers.ChangeSecondsValue;
import static ngn.controller.Timers.WaitForClient;
import ngn.text.Config;
import ngn.view.Card;
import ngn.view.Litrs;
import ngn.view.Pin;
import ngn.view.Wait;

/**
 *
 * @author Офис
 */
public class KeyPad {

    public static SerialPort KeyPadCOM4;
    public static String AdminPass = "";

    public KeyPad() {
        KeyPadSettings();
        KyePadWorks.start();
    }

    public static void KeyPadSettings() {
        KeyPadCOM4 = new SerialPort(PortCheck.KPPort);
        try {
            KeyPadCOM4.openPort();
            KeyPadCOM4.setParams(2400, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            KeyPadCOM4.setEventsMask(SerialPort.MASK_RXCHAR);
            KeyPadCOM4.addEventListener(new EventListener());
        } catch (SerialPortException ex) {
            System.out.println(ex);
        }
    }

    public static class EventListener implements SerialPortEventListener {

        @Override
        public void serialEvent(SerialPortEvent event) {
            if (event.isRXCHAR() && event.getEventValue() != 0) {
                try {
                    String dataCOM4 = KeyPadCOM4.readHexString(1);
                    if (dataCOM4.contains("25")) {
                        String knopkaHex = KeyPadCOM4.readHexString(2);

                        String KNOPKA = String.valueOf(knopkaHex.charAt(4));
                        //ADMIN PASS IS HERE
                        if (CheckVisibility().equals("EnterCard")) {
                            AdminPass += KNOPKA;
                            if (AdminPass.equals(Config.ADMIN_PASS)) {
                                AdminPass = "";
                                Variables.Admin();
                                Litrs.ClientName.setText(Variables.name);
                                Litrs.ClientCard.setText(Variables.code);
                                Litrs.ClientLitrs.setText(Variables.litrnum);
                                ChangePanel.ShowPanel(Litrs.EnterLitrs);
                                ChangePanel.FocusLitrsInput();
                            }
                            if (AdminPass.length() == 8 || "A".equals(KNOPKA)) {
                                AdminPass = "";
                            }
                        }
                        //ADMIN PASS END HERE
                        if (CheckVisibility().equals("Waiting") && KNOPKA != null) {
                            ChangePanel.ShowPanel(Card.EnterCard);
                            ChangePanel.FocusPassword(Card.CardCode);
                            ToZero.TextOff();
                            Wait.Waiting.setFocusable(false);
                            ChangeSecondsValue.stop();
                            WaitForClient.stop();
                        } else if ("B".equals(KNOPKA) && (CheckVisibility().equals("EnterPin") || CheckVisibility().equals("EnterLitrs"))) {
                            try {
                                Robot robot = new Robot();
                                robot.keyPress(KeyEvent.VK_ENTER);
                                robot.keyRelease(KeyEvent.VK_ENTER);
                            } catch (AWTException ex) {
                                System.out.println(ex);
                            }
                        } else if ("A".equals(KNOPKA) && !CheckVisibility().equals("Working")) {
                            if (CheckVisibility().equals("EnterPin") || CheckVisibility().equals("EnterLitrs")) {
                                Timers.WaitForClient.stop();
                                ToZero.TextOff();
                                ChangePanel.ShowPanel(Card.EnterCard);
                                ChangePanel.FocusPassword(Card.CardCode);
                            }
                        } else {
                            if (CheckVisibility().equals("EnterPin")) {
                                char[] p = Pin.PinCode.getPassword();
                                String pin = String.copyValueOf(p);
                                Pin.PinCode.setText(pin + KNOPKA);
                            }
                            if (CheckVisibility().equals("EnterLitrs")) {
                                String enterl = Litrs.LitrsInput.getText();
                                Litrs.LitrsInput.setText(enterl + KNOPKA);
                            }
                        }
                    }
                    if (dataCOM4.contains("23")) {
                        String kartaHex = KeyPadCOM4.readHexString(10);
                        char[] kartaArray = new char[10];
                        int k = 0;
                        for (int i = 1; i < kartaHex.length(); i += 3) {
                            kartaArray[k] = kartaHex.charAt(i);
                            k++;
                        }

                        String KARTA = String.valueOf(kartaArray);
                        if (CheckVisibility().equals("EnterCard")) {
                            Ngn.SetActiveNgn();
                            Card.CardCode.setText(KARTA);
                            try {
                                Robot robot = new Robot();
                                robot.keyPress(KeyEvent.VK_ENTER);
                                robot.keyRelease(KeyEvent.VK_ENTER);
                            } catch (AWTException ex) {
                                System.out.println(ex);
                            }
                        }
                    }
                } catch (SerialPortException ex) {
                    System.out.println(ex);
                }
            }
        }
    }

}
