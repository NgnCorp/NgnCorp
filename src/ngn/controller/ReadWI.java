package ngn.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Офис
 */
public class ReadWI {
    
    public static void ReadWI() {
        try(BufferedReader br = new BufferedReader (new FileReader("D:/FillingData.txt")))
        {
           // чтение посимвольно
            StringBuilder allText = new StringBuilder();
            char[] buff = new char[1];
            int c;
            while((c=br.read(buff))!=-1) {
                allText.append(buff, 0, c);
            }
            System.out.println(allText);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        } 
    }
}
