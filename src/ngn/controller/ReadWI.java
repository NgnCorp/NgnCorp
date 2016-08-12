package ngn.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Офис
 */
public class ReadWI {
    
    public void ReadWI() {
        try(BufferedReader br = new BufferedReader (new FileReader("D:/FillingData.txt")))
        {
           // чтение посимвольно
            int c;
            while((c=br.read())!=-1) {
                System.out.print((char)c);
            }
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        } 
    }
}
