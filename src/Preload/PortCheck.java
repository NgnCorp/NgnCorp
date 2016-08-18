package Preload;

import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import jssc.SerialPortList;

/**
 *
 * @author Офис
 */
public class PortCheck {

    static String[] portNames;
    static String PN;
    static SerialPort PortToCheck;
    public static String GSPort;
    public static String KPPort;
    static String data;
    static Integer n = 0;

    static Timer WaitForAnswer;

    public static boolean PortCheck() {

        portNames = SerialPortList.getPortNames();
        for (String portName : portNames) {
            System.out.println(portName);
            PN = portName;
            PortToCheck = new SerialPort(portName);
            try {
                PortToCheck.openPort();
                PortToCheck.addEventListener(new PortListener());
                DoWithPort(0);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                DoWithPort(1);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                System.out.println(GSPort + " " + KPPort);
            } catch (SerialPortException ex) {
                System.out.println(ex);
            }
        }
        return true;
    }

    public static void DoWithPort(int trying) {
        try {
            if (trying == 0) {
                System.out.println("2400");
                n = 20;
                PortToCheck.setParams(2400, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                PortToCheck.writeString("programming");
            }
            if (trying == 1) {
                System.out.println("9600");
                n = 12;
                PortToCheck.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_MARK);
                PortToCheck.setEventsMask(SerialPort.MASK_RXCHAR);
                PortToCheck.writeString("@10510045#");
            }
        } catch (SerialPortException ex) {
            System.out.println(ex);
        }
    }

    private static class PortListener implements SerialPortEventListener {

        @Override
        public void serialEvent(SerialPortEvent spe) {
            if (spe.isRXCHAR() && spe.getEventValue() != 0) {
                try {
                    data = PortToCheck.readString(n);
                    System.out.println(data);
                    if (data.contains("V")) {
                        KPPort = PN;
                        PortToCheck.writeString("1Q");
                    }
                    if (data.contains("@")) {
                        GSPort = PN;
                    }
                } catch (SerialPortException ex) {
                    System.out.println(ex);
                }
            }

        }
    }
}
