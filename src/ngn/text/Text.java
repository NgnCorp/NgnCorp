package ngn.text;

/**
 *
 * @author Офис
 */
public class Text {

    //___________E R R O R S    T E X T___________//
    
    public static  String nointernetatstart = "<html><p style=\"text-align:center;\">ОТСУТСТВУЕТ СВЯЗЬ С СЕРВЕРОМ!<br>ЖДЕМ 15 СЕКУНД.</p>";
    public static  String nointernetinend   = "<html><p style=\"text-align:center;\">ОТСУТСТВУЕТ СВЯЗЬ С ИНТЕРНЕТОМ!<br>ТРАНЗАКЦИЯ ПРОЙДЕТ ПОСЛЕ ПОЯВЛЕНИЯ СВЯЗИ.</p>";
    public static  String cardvalid         = "<html><p style=\"text-align:center;\">КАРТА НЕ ПРОШЛА ПРОВЕРКУ!<br>ПОПРОБУЙТЕ ЕЩЕ РАЗ ПОЗЖЕ.</p>";
    public static  String needlitres        = "<html><p style=\"text-align:center;\">ЛИМИТ ЛИТРОВ ПО КАРТЕ ИСЧЕРПАН!<br>ПОПОЛНИТЕ КАРТУ.</p>";
    public static  String pin               = "<html><p style=\"text-align:center;\">PIN-КОД ВВЕДЕН НЕ ВЕРНО!<br>ПОПРОБУЙТЕ ЕЩЕ РАЗ.</p>";
    public static  String numlitres         = "<html><p style=\"text-align:center;\">НЕ ВЕРНОЕ ЧИСЛО ЛИТРОВ!<br>ПОПРОБУЙТЕ ЕЩЕ РАЗ.</p>";
    public static  String notenoughlitres   = "<html><p style=\"text-align:center;\">НЕДОСТАТОЧНО ЛИТРОВ НА КАРТЕ!<br>ПОПОЛНИТЕ КАРТУ.</p>";
    public static  String getpistol         = "<html><p style=\"text-align:center\">Поднимите пистолет и вставьте в бак.<br>Затем снова нажмите кнопку #</p>";
    public static  String technic           = "<html><p style=\"text-align:center\">ТЕХНИЧЕСКИЕ НЕПОЛАДКИ!<br>ПОЗВОНИТЕ ПО НОМЕРУ +38(093)674-64-54.</p>";
    
    //___________P A N E L S    T E X T___________//
    
    public static String h1CardPanel    = "<html><p style=\"margin-bottom:20px\">Поднесите карту к клавиатуре";
    public static String h1EnterPin     = "<html><p style=\"margin-bottom:20px\">Введите PIN-код";
    public static String h1ExitStar     = "<html>Нажмите звездочку * на клавиатуре для ВЫХОДА.";
    public static String h1Confirm      = "<html><p style=\"margin-top:20px\">Для ПОДТВЕРЖДЕНИЯ pin нажмите решетку # на клавиатуре.";
    public static String h1SetLitrs     = "<html>Введите количество литров";
    public static String h1CardOwner    = "<html>Владелец карты:";
    public static String h1CardNum      = "<html>Номер карты:";
    public static String h1LitrsStorage = "<html>Остаток литров:";
    public static String h1StartFilling = "<html>Нажмите решетку (#) на клавиатуре для ПУСКА заправки.";
    public static String h1CountLitrs   = "<html>СЧЕТ ЛИТРОВ";
    public static String h1UAH          = "<html>ГРН: ";
    public static String h1TYforChoose  = "<html><p style=\"text-align:center;\">Транзакция прошла успешно!<br>Спасибо, что воспользовались услугой компании NGN!</p>";
    public static String h1AreUHere     = "<html><p style=\"text-align:center;\">Вы еще здесь?</p>";
    public static String h1ClickIfUHere = "<html><p style=\"text-align:center;\">Нажмите любую кнопку на клавиатуре.<br>Осталось: 15 секунд.</p>";
    public static String h1Yes          = "<html>ДА";
    public static String h1LostIntrCon  = "<html>Подождите. Пропал интернет. Скоро появится...";
    
    //___________G A S  S T A T I O N    T E X T___________//
    
    public static String rememberAboutPistol  = "НЕ ЗАБУДЬТЕ ПОВЕСИТЬ ПИСТОЛЕТ ПОСЛЕ ЗАПРАВКИ!";
    public static String pistolOnGS           = "ПИСТОЛЕТ ПОВЕШЕН";
    
     //__________P R E L O A D   T E X T____________//
    public static String h1BeforeStart      = "<html>Подождите. Идет настройка приложения...";
    public static String h1CheckFacilities  = "<html>Определяем оборудование...";
    public static String PortsON            = "<html>Оборудование на месте";
    public static String h1CheckUpdate      = "<html>Проверка обновления...";
    public static String cantConn           = "<html>Не удается подключится к серверу!";
    public static String tryConnInet        = "<html>Проверяем наличие интернета...";
    public static String cantConnInet       = "<html>Нет подключения. Запуск приложения без интернета!";
    public static String authNOT            = "<html>Авторизация не пройдена!";
    public static String InetOkTryDownload  = "<html>Интернет есть! Скачиваем обновление...";
    public static String authSUCS           = "<html>Авторизация прошла успешно!";
    public static String downlNEW           = "<html>Скачивание новой версии программы";
    public static String cantFIND           = "<html>Не удается найти файл: ";
    public static String createFile         = "<html>Файл создан: ";
    public static String cantCREATE         = "<html>Не удается создать файл: ";
    public static String workingFIREWALL    = "<html>Брандмауэр не разрешил создание файла: ";
    public static String cantdownlNEW       = "<html>Не удалось скачать обновление";
    public static String cantRunProg        = "<html>Невозможно открыть програму обновления";
    public static String createLDB          = "<html>Проверяем данные с сервера";
    public static String LDBdone            = "<html>Проверка данных завершена";
    public static String h1SettingsDone     = "<html>Настройка завершена";
    
    // PORTS ERRORS
    public static String KPPortOff  = "<html>Клавиатура не найдена!";
    public static String GSPortOff  = "<html>Колонка не найдена!";
    
    //READ\WRITE ERRORS
    public static String cannotreadTR     = "Не удалось прочитать данные по транзакциям.";
    public static String cannotreadDB     = "Не удалось прочитать данные по клиенту.";    
    public static String cannotConSer     = "Сервер не ответил.";
    
    //__________D I N A M I C   T E X T____________//
    
    public static String WaitingText    = "<html><p style=\"text-align:center;\">Нажмите любую кнопку на клавиатуре.<br>Осталось: ";
    public static String ServerText     = "<html><p style=\"text-align:center;\">ОТСУТСТВУЕТ СВЯЗЬ С СЕРВЕРОМ!<br>ЖДЕМ ";
    
    //__________C R E A T O R   T E X T____________//
    public static String SignatureText  = "<html><p style=\"text-align:left;\">Designed & developed  by SvinTeger.com";
    public static String BeginingTime   = "<html><p style=\"padding-right:0px;\">--:--:--";
    public static String DatePadding    = "<html><p style=\"padding-right:0px;\">";
    public static String TimeToReload   = "23:00:";
    public static final String HFP      = "Hello From Past";
}
