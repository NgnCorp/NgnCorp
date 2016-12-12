package ngn.text;

import java.net.URL;

/**
 *
 * @author Валерик
 */
public class Paths {

    //___________F I L E S    P A T H___________//
    public static final String TRANSACTIONPATH  = "C:\\NgnUpdater\\FillingData.txt";
    public static final String LDBPATH          = "C:\\NgnUpdater\\LDB.txt";
    public static final String CACHELDBPATH     = "C:\\NgnUpdater\\CACHELDB.txt";
    public static final String MODULENAMEPATH   = "C:\\NgnUpdater\\ModuleName.txt";
    public static final String LOGPATH          = "C:\\NgnUpdater\\LogFile.txt";
    public static final String UPDATEPATH       = "C:\\NgnUpdater\\dist\\Unzip.exe";
    public static final String COUNTERPATH      = "C:\\NgnUpdater\\Counter.txt";
    public static final String CACHEDATAPATH    = "C:\\NgnUpdater\\CacheData.txt";
    public static final String GSCACHEPATH      = "C:\\NgnUpdater\\GSCache.txt";

    //___________I M A G E S   P A T H___________//
    public static URL INETON;
    public static URL INETOFF;
    public static URL KEYPADON;
    public static URL KEYPADOFF;
    public static URL PISTOLON;
    public static URL PISTOLOFF;
    public static URL SERVERON;
    public static URL SERVEROFF;
    public static URL SERVERCON;
    
    public Paths(){
        INETON           = getClass().getResource("/images/internetON.png");
        INETOFF          = getClass().getResource("/images/internetOFF.png");
        KEYPADON         = getClass().getResource("/images/keypadON.png");
        KEYPADOFF        = getClass().getResource("/images/keypadOFF.png");
        PISTOLON         = getClass().getResource("/images/pistolON.png");
        PISTOLOFF        = getClass().getResource("/images/pistolOFF.png");
        SERVERON         = getClass().getResource("/images/serverON.png");
        SERVEROFF        = getClass().getResource("/images/serverOFF.png");
        SERVERCON        = getClass().getResource("/images/serverCON.png");
    }

}
