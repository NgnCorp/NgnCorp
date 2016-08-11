package ngn.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Офис
 */
public class readWithoutInternet {
    
    public void readWithoutInternet() {
        try(BufferedReader br = new BufferedReader (new FileReader("/FillingData.txt")))
        {
            int c;
            while((c=br.read())!=-1){
                
            }
        }
        catch(IOException ex){
            ex.getMessage();
        } 
    }
}
