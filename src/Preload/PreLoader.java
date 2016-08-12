package Preload;

import ngn.controller.InternetConn;

public class PreLoader {

    // ПРОВЕРКА НАЛИЧИЯ COM ПОРТОВ (операции по предзагрузки приложения)
    // Обновление приложения
    // Запись в БД даных записаных без интернета на модуле
    // Ожидание ответа сервера
    public static boolean PreLoader() {
        PortCheck.PortCheck();
        return InternetConn.InternetConn();
    }

}
