package Preload;

import static Preload.BackendTimers.WaitForAnswer;
import static Preload.PortCheck.PortCheck;
import static ngn.view.BeforeStart.LoadingText;

public class PreLoader {

    // ПРОВЕРКА НАЛИЧИЯ COM ПОРТОВ (операции по предзагрузки приложения)
    // Обновление приложения
    // Запись в БД даных записаных без интернета на модуле
    // Ожидание ответа сервера
    public static void PreLoader() {
        
        LoadingText.setText("Настройка приложения");
        //if (PortCheck.PortCheck()) {
            //System.out.println(GSPort + " " + KPPort);
        //}
        //InternetConn.InternetConn();
        WaitForAnswer();
        PortCheck();
    }
}
