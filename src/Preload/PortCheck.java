package Preload;

import static Preload.Update.CheckLocalDB;
import java.util.Locale;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import jssc.SerialPortList;
import mail.SendMail;
import ngn.Ngn;
import ngn.controller.GasStation;
import ngn.controller.ReadWI;
import ngn.controller.WriteWI;
import ngn.text.Paths;
import ngn.text.Text;
import static ngn.view.BeforeStart.BSLoadingText;

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
    static Integer NumberOfSymbols = 0;

    public static void PortCheck() {

        portNames = SerialPortList.getPortNames();
        for (String portName : portNames) {
            System.out.println(portName);
            PN = portName;
            PortToCheck = new SerialPort(portName);
            try {
                PortToCheck.openPort();
                PortToCheck.addEventListener(new PortListener());
                DoWithPort("GS");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                BSLoadingText.setText(Text.h1CheckFacilities);

                DoWithPort("KP");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            } catch (SerialPortException ex) {
                System.out.println(ex);
            } finally {
                try {
                    PortToCheck.closePort();
                } catch (SerialPortException ex) {
                    System.out.println(ex);
                }
            }
        }
        if (GSPort != null) {
            if (KPPort != null) {
                BSLoadingText.setText(Text.PortsON);
                if (Update.CheckCacheData()) {
                    ReadWI.CacheData[4] = String.format(Locale.ENGLISH, "%.2f", GasStation.GScounter - Double.valueOf(ReadWI.CacheData[4]));
                    WriteWI.Write(ReadWI.CacheData, Paths.TRANSACTIONPATH, true);// Записываем операцию в FillingData.txt
                    WriteWI.CacheDataToZero();
                }
                if (BackendTimers.InternetCheck) {
                    Threads.LOCALDB();
                } else if (CheckLocalDB()) {
                    BSLoadingText.setText(Text.LDBdone);
                } else {
                    BSLoadingText.setText(Text.LDBNotdone);                    
                }
            } else {
                BSLoadingText.setText(Text.KPPortOff);
                Ngn.StatusBar(Paths.KEYPADOFF, 2);
            }
        } else {
            BSLoadingText.setText(Text.GSPortOff);
            Ngn.StatusBar(Paths.PISTOLOFF, 3);
        }
    }

    public static void DoWithPort(String port) {
        try {
            if (port.equals("KP")) {
                System.out.println("2400");
                NumberOfSymbols = 20;
                PortToCheck.setParams(2400, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                PortToCheck.writeString("programming");
            }
            if (port.equals("GS")) {
                System.out.println("9600");
                NumberOfSymbols = 19;
                PortToCheck.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_MARK);
                PortToCheck.setEventsMask(SerialPort.MASK_RXCHAR);
                PortToCheck.writeString("@1054010140#");
            }
        } catch (SerialPortException ex) {
            System.out.println(ex);
        }
    }

    public static void ClosePorts() {
        for (String portName : portNames) {
            PortToCheck = new SerialPort(portName);
            if (PortToCheck.isOpened()) {
                try {
                    PortToCheck.closePort();
                } catch (SerialPortException ex) {
                    System.out.println(ex);
                }
            }
        }
    }

    private static class PortListener implements SerialPortEventListener {

        @Override
        public void serialEvent(SerialPortEvent spe) {
            if (spe.isRXCHAR() && spe.getEventValue() != 0) {
                try {
                    data = PortToCheck.readString(NumberOfSymbols);
                    System.out.println(data);
                    if (data.contains("V")) {
                        KPPort = PN;
                        Ngn.StatusBar(Paths.KEYPADON, 2);
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            System.out.println(e);
                        }
                        PortToCheck.writeString("1Q");
                    }
                    if (data.contains("@")) {
                        GSPort = PN;
                        Ngn.StatusBar(Paths.PISTOLON, 3);
                        Integer Litrs = Integer.decode("0x" + new String(data.toCharArray(), 9, 8));
                        Integer MiliLitrs = Integer.decode("0x" + new String(data.toCharArray(), 17, 2));
                        GasStation.GScounter = Double.valueOf(Litrs + "." + MiliLitrs);
                    }
                } catch (SerialPortException ex) {
                    System.out.println(ex);
                }
            }

        }
    }
}
