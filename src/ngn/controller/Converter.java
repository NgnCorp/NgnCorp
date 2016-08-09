package ngn.controller;

/**
 *
 * @author Офис
 */
public class Converter {
    
    public Converter() {
    
    }
    
 public static String ConvertToHex(String litrs) {
        //int convertLitrs = Integer.valueOf(litrs); // Вытягиваем значение вводимых клиентом литров
        //int mililitr = convertLitrs * 100;
        String hexMililitrs = Integer.toHexString(Integer.valueOf(litrs) * 100);
        //int hexLength = hexMililitrs.length();
        char[] litrHex = new char[8];
        int needlenght = 8;
        int stoplength = litrHex.length - hexMililitrs.length();
        for (; stoplength >= 0; stoplength--) {
            litrHex[stoplength] = '0';
        }
        stoplength = needlenght - hexMililitrs.length();
        int d = 0;
        for (; stoplength < 8; stoplength++) {
            litrHex[stoplength] = hexMililitrs.charAt(d);
            d++;
        }
        String lhex = new String(litrHex); // Хексовое значение кол-ва литров без контрольной суммы
        String eqHex = lhex.toUpperCase();
        //String eqHex = newlhex; // Блок из восьми чисел для колонки
        return eqHex;
    }

    public static boolean ConvertToDouble(String litrs, String litrnum) {
        double doublevalue = Double.valueOf(litrnum) - Double.valueOf(litrs);
        return doublevalue >= 0;
    }

    public static String HexDozaForKolonka(String eqHex) {
        String coding = "@10440501" + eqHex;
        int decnum = coding.length();
        char[] decimal = new char[decnum];
        int crc = 0;
        for (int col = 0; col < decnum; col++) {
            decimal[col] = coding.charAt(col);
            crc ^= decimal[col]; // CRC (контрольная сумма)
        }
        String pihex = Integer.toHexString(crc);
        String komDoza = "@10440501" + eqHex + pihex + "#";
        return komDoza;
    }
}
