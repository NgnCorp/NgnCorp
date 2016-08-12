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

    public static void PortCheck() {
        portNames = SerialPortList.getPortNames();
        for (int i = 0; i < portNames.length; i++) {
            SerialPort serialPort = new SerialPort(portNames[i]);
            try {
                serialPort.openPort();
                serialPort.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_MARK);
                serialPort.writeString("@10510045#");
                String oneChar = serialPort.readString(1);
                if (oneChar.equals("@")){
                    
                } else {
                    serialPort.setParams(2400, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                    oneChar = serialPort.readHexString(1);
                    if (oneChar.contains("25")) {
                        
                    }
                }
                serialPort.closePort();
            } catch (SerialPortException ex) {
                System.out.println(ex);
            }
        }
    }
}
