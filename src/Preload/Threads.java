/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Preload;

/**
 *
 * @author Валерик
 */
public class Threads {

    static Runnable runCheckPorts;
    static Runnable runUpdate;
    static Runnable runLocalDB;
    static Runnable runInternetConn;
    public static Thread CheckPorts;
    public static Thread Upd;
    public static Thread LocalDb;
    public static Thread InternetConn;

    public Threads() {

        runUpdate = () -> {
            Update.Update();
        };

        runCheckPorts = () -> {
            // Settings for App //
            PreLoader.PreLoader();
        };

        runLocalDB = () -> {
            LocalDB.LocalDB();
        };
        
        runInternetConn = () -> {
            BackendTimers.InternetStatus();
        };
    }

    public static void UPD() {
        Upd = null;
        Upd = new Thread(runUpdate);
        Upd.start();
    }

    public static void CHECKPORTS() {
        CheckPorts = null;
        CheckPorts = new Thread(runCheckPorts);
        CheckPorts.start();
    }

    public static void LOCALDB() {
        LocalDb = null;
        LocalDb = new Thread(runLocalDB);
        LocalDb.start();
    }

    public static void INTERNETCONN() {
        InternetConn = null;
        InternetConn = new Thread(runInternetConn);
        InternetConn.start();
    }
}