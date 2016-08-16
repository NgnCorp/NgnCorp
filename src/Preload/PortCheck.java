package Preload;

import java.util.Arrays;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

/**
 *
 * @author Офис
 */
public class PortCheck {

    static String[] portNames;
    static SerialPort PortToCheck;
    public static String GSPort;
    public static String KPPort;

    public static boolean PortCheck() {
        System.out.println("PorCheck Starts");
        portNames = SerialPortList.getPortNames();
        for (String portName : portNames) {
            System.out.println(portName);
            PortToCheck = new SerialPort(portName);
            try {
                PortToCheck.openPort();
                PortToCheck.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_MARK);
                PortToCheck.writeString("@10510045#");
                String oneChar = PortToCheck.readString(1);
            System.out.println(oneChar);
                if (oneChar.equals("@")) {
                    GSPort = portName;
                } else {
                    PortToCheck.setParams(2400, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                    oneChar = PortToCheck.readHexString(1);
                    if (oneChar.contains("25")) {

                    } else {
                        System.out.println("s");
                    }
                }
                PortToCheck.closePort();
            } catch (SerialPortException ex) {
                System.out.println(ex);
            }
        }
        if (GSPort == null) {
            System.out.println("Kolonka ne podkluchena");
        }
        if (KPPort == null) {
            System.out.println("Klava ne podkluchena");
        }
        System.out.println(GSPort + " " + KPPort);
        return true;
    }
}
