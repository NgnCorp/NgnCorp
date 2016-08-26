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
    public static Thread CheckPorts;
    public static Thread Upd;
    public static Thread LocalDb;

    public Threads() {

        runUpdate = () -> {
            Update.Update();
        };
        Upd = new Thread(runUpdate);

        runCheckPorts = () -> {
            // Settings for App //
            PreLoader.PreLoader();
        };
        CheckPorts = new Thread(runCheckPorts);

        runLocalDB = () -> {
            LocalDB.LocalDB();
        };
        LocalDb = new Thread(runLocalDB);

    }

    public static void UPD() {
        Upd.start();
    }

    public static void CHECKPORTS() {
        CheckPorts.start();
    }

    public static void LOCALDB() {
        LocalDb.start();
    }

}
