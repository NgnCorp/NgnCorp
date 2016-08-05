package ngn.text;

/**
 *
 * @author Офис
 */
public class Text {

    //___________E R R O R S    T E X T___________//
    
    static String nointernetatstart = "<html><p style=\"text-align:center;\">ОТСУТСТВУЕТ СВЯЗЬ С СЕРВЕРОМ!<br>ЖДЕМ 15 СЕКУНД.</p>";
    static String nointernetinend   = "<html><p style=\"text-align:center;\">ОТСУТСТВУЕТ СВЯЗЬ С ИНТЕРНЕТОМ!<br>ТРАНЗАКЦИЯ ПРОЙДЕТ ПОСЛЕ ПОЯВЛЕНИЯ СВЯЗИ.</p>";
    static String cardvalid         = "<html><p style=\"text-align:center;\">КАРТА НЕ ПРОШЛА ПРОВЕРКУ!<br>ПОПРОБУЙТЕ ЕЩЕ РАЗ ПОЗЖЕ.</p>";
    static String needlitres        = "<html><p style=\"text-align:center;\">ЛИМИТ ЛИТРОВ ПО КАРТЕ ИСЧЕРПАН!<br>ПОПОЛНИТЕ КАРТУ.</p>";
    static String pin               = "<html><p style=\"text-align:center;\">PIN-КОД ВВЕДЕН НЕ ВЕРНО!<br>ПОПРОБУЙТЕ ЕЩЕ РАЗ.</p>";
    static String numlitres         = "<html><p style=\"text-align:center;\">НЕ ВЕРНОЕ ЧИСЛО ЛИТРОВ!<br>ПОПРОБУЙТЕ ЕЩЕ РАЗ.</p>";
    static String notenoughlitres   = "<html><p style=\"text-align:center;\">НЕДОСТАТОЧНО ЛИТРОВ НА КАРТЕ!<br>ПОПОЛНИТЕ КАРТУ.</p>";
    static String getpistol         = "<html><p style=\"text-align:center\">Поднимите пистолет и вставьте в бак.<br>Затем снова нажмите кнопку #</p>";
    static String technic           = "<html><p style=\"text-align:center\">ТЕХНИЧЕСКИЕ НЕПОЛАДКИ!<br>ПОЗВОНИТЕ ПО НОМЕРУ +38(093)674-64-54.</p>";

    //___________P A N E L S    T E X T___________//
    
    public static String h1CardPanel    = "<html>Поднесите карту к клавиатуре";
    public static String h1EnterPin     = "<html>Введите PIN-код";
    public static String h1ExitStar     = "<html>Нажмите звездочку (*) на клавиатуре для ВЫХОДА.";
    public static String h1Confirm      = "<html>Для ПОДТВЕРЖДЕНИЯ pin-кода нажмите решетку (#) на клавиатуре.";
    public static String h1SetLitrs     = "<html>Введите количество литров";
    public static String h1CardOwner    = "<html>Владелец карты:";
    public static String h1CardNum      = "<html>Номер карты:";
    public static String h1LitrsStorage = "<html>Остаток литров:";
    public static String h1StartFilling = "<html>Нажмите решетку (#) на клавиатуре для ПУСКА заправки.";
    public static String h1CountLitrs   = "<html>СЧЕТ ЛИТРОВ";
    public static String h1UAH          = "<html>ГРН:";
    public static String h1TYforChoose  = "<html><p style=\"text-align:center;\">Транзакция прошла успешно!<br>Спасибо, что воспользовались услугой компании NGN!</p>";
    public static String h1AreUHere     = "<html><p style=\"text-align:center;\">Вы еще здесь?</p>";
    public static String h1ClickIfUHere = "<html><p style=\"text-align:center;\">Нажмите любую кнопку на клавиатуре.<br>Осталось: 15 секунд.</p>";
    public static String h1Yes          ="<html>ДА";
    public static String h1LostIntrCon  = "<html>Подождите. Пропал интернет. Скоро появится...";
}
