package Preload;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                PortToCheck.setParams(2400, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                PortToCheck.addEventListener(new PortListener());
                PortToCheck.writeString("programming");
                try {
                    PortToCheck.wait(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(PortCheck.class.getName()).log(Level.SEVERE, null, ex);
                }
                PortToCheck.writeString("kill");
                byte[] data = PortToCheck.readBytes();
                System.out.println(Arrays.toString(data));
                //PortToCheck.writeString("kill");
                /*
                String oneChar = PortToCheck.readString(1);
                if (oneChar.equals("@")) {
                    GSPort = portName;
                } else {
                    PortToCheck.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_MARK);
                    PortToCheck.writeString("@10510045#");
                    System.out.println(oneChar);
                    if (oneChar.contains("25")) {

                    } else {
                        System.out.println("s");
                    }
                }
                 */
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

    public static class PortListener implements SerialPortEventListener {

        @Override
        public void serialEvent(SerialPortEvent spe) {
            if (spe.isRXCHAR() && spe.getEventValue() != 0) {
                try {
                    String data = PortToCheck.readString();
                    System.out.println(data);
                    //PortToCheck.writeString("kill");
                } catch (SerialPortException ex) {
                }
            }
        }

    }
}
