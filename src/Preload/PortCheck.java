package Preload;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

/**
 *
 * @author Офис
 */
public class PortCheck {
        static String[] portNames;
        public static String GSPort;
        public static String KPPort;

    public static void PortCheck() {
        portNames = SerialPortList.getPortNames();
            for (String portName : portNames) {
                System.out.println(portName);
                /*
                try {
                    serialPort.openPort();
                    serialPort.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_MARK);
                    serialPort.writeString("@10510045#");
                    String oneChar = serialPort.readString(1);
                    if (oneChar.equals("@")) {
                        GSPort = portName;
                    } else {
                        serialPort.setParams(2400, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                        oneChar = serialPort.readHexString(1);
                        if (oneChar.contains("25")) {
                            
                        } else {
                            System.out.println("s");
                        }
                    }
                    serialPort.closePort();
                }catch (SerialPortException ex) {
                    //System.out.println(ex);
                }
*/
            }
        if (GSPort == null){
            System.out.println("Kolonka ne podkluchena");
        }
        if(KPPort == null) {
            System.out.println("Klava ne podkluchena");
        }
            System.out.println(GSPort + " " + KPPort);
    }
}
