package ngn.controller;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 *
 * @author Офис
 */
public class KeyPad {

    private static SerialPort KyePadCOM4;

    public KeyPad() {
        KyePadCOM4 = new SerialPort("COM4");
        try {
            KyePadCOM4.openPort();
            KyePadCOM4.setParams(2400, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            KyePadCOM4.setEventsMask(SerialPort.MASK_RXCHAR);
            KyePadCOM4.addEventListener(new EventListener());
        } catch (SerialPortException ex) {
            System.out.println(ex);
        }
    }

    private class EventListener implements SerialPortEventListener {

        @Override
        public void serialEvent(SerialPortEvent event) {
            if (event.isRXCHAR() && event.getEventValue() != 0) {
                try {
                    String dataCOM4 = KyePadCOM4.readHexString(1);
                    /*
                    if (dataCOM4.contains("25") && fokus) {
                        String knopkaHex = KyePadCOM4.readHexString(2);

                        String KNOPKA = String.valueOf(knopkaHex.charAt(4));
                        if (Waiting.isVisible() && KNOPKA != null) {
                            try {
                                Robot robot = new Robot();
                                robot.keyPress(KeyEvent.VK_ENTER);
                                robot.keyRelease(KeyEvent.VK_ENTER);
                            } catch (AWTException ex) {
                            }
                        } else if ("B".equals(KNOPKA)) {
                            try {
                                Robot robot = new Robot();
                                robot.keyPress(KeyEvent.VK_ENTER);
                                robot.keyRelease(KeyEvent.VK_ENTER);
                            } catch (AWTException ex) {
                            }
                        } else if ("A".equals(KNOPKA) && !Working.isVisible()) {
                            if (toBeorNottoBe) {
                            } else {
                                youHere.stop();
                                PinCode.setText("");
                                CardCode.setText("");
                                EnterPin.setVisible(false);
                                EnterLitrs.setVisible(false);
                                GoodBye.setVisible(false);
                                InfoMassage.setVisible(false);
                                PinCode.setFocusable(false);
                                LitrsInput.setFocusable(false);
                                EnterCard.setVisible(true);
                                CardCode.setFocusable(true);
                                CardCode.requestFocusInWindow();
                            }
                        } else {
                            char[] p = PinCode.getPassword();
                            String pin = String.copyValueOf(p);
                            PinCode.setText(pin + KNOPKA);
                            String enterl = LitrsInput.getText();
                            LitrsInput.setText(enterl + KNOPKA);
                        }
                    }
                    if (dataCOM4.contains("23")) {
                        String kartaHex = KyePadCOM4.readHexString(10);
                        char[] kartaArray = new char[10];
                        int k = 0;
                        for (int i = 1; i < kartaHex.length(); i += 3) {
                            kartaArray[k] = kartaHex.charAt(i);
                            k++;
                        }

                        String KARTA = String.valueOf(kartaArray);
                        if (EnterCard.isVisible()) {
                            CardCode.setText(KARTA);
                            try {
                                Robot robot = new Robot();
                                robot.keyPress(KeyEvent.VK_ENTER);
                                robot.keyRelease(KeyEvent.VK_ENTER);
                            } catch (AWTException ex) {
                            }
                        }
                    }
*/
                } catch (SerialPortException ex) {
                }
            }
        }
    }

}
