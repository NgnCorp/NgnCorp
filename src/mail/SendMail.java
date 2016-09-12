package mail;

import static Preload.BackendTimers.InternetCheck;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import ngn.controller.WriteWI;
import ngn.text.Paths;

/**
 * @author Svitlyk
 */
public class SendMail {

    private static final String FROM = "ngnservise@mail.ru";
    private static final String SMTPHOST = "smtp.mail.ru";
    private static final String PORT = "2525";
    private static final String LOGIN = "ngnservise@mail.ru";
    private static final String PASSWORD = "NgnAdmin2015";
    private static final String TOVALERA = "lcf666666lcf@gmail.com";
    private static final String TOSASHA = "svitlyk.oleksandr@mail.ru";
    private static Session mailSession;
    private static MimeMessage message;
    private static Multipart mp;
    private static MimeBodyPart mbp;

    public static void sendEmail(String body, String subject) {
        if (InternetCheck) {
            Properties mailProps = new Properties();
            mailProps.put("mail.smtp.host", SMTPHOST);
            mailProps.put("mail.smtp.from", FROM);
            mailProps.put("mail.smtp.port", PORT);
            mailProps.put("mail.smtp.auth", true);
            //mailProps.put("mail.smtp.socketFactory.port", PORT);
            //mailProps.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            //mailProps.put("mail.smtp.socketFactory.fallback", "false");
            mailProps.put("mail.smtp.starttls.enable", "true");

            mailSession = Session.getDefaultInstance(mailProps, new Authenticator() {

                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(LOGIN, PASSWORD);
                }
            });

            message = new MimeMessage(mailSession);
            int b = 1;
            try {
                message.setFrom(new InternetAddress(FROM));
            } catch (MessagingException ex) {
                Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
                b = 0;
            }
            if (b == 1) {
            String[] emails = {TOVALERA, TOSASHA};
            for (String email : emails) {
                try {
                    message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(TOVALERA + "," + TOSASHA));
                    message.setSubject(subject, "UTF-8");
                    mp = new MimeMultipart();
                    mbp = new MimeBodyPart();
                    mbp.setContent(body, "text/html;charset=utf-8");
                    mp.addBodyPart(mbp);
                    message.setContent(mp);
                    message.setSentDate(new java.util.Date());

                    Transport.send(message);
                } catch (AddressException ex) {
                    String[] logMas = {"Adress", String.valueOf(ex)};
                    WriteWI.Write(logMas, Paths.LOGPATH, true);
                } catch (MessagingException ex) {
                    String[] logMas = {"Message", String.valueOf(ex)};
                    WriteWI.Write(logMas, Paths.LOGPATH, true);
                }
            }
            } else {
            String[] logMas = {subject, body};
            WriteWI.Write(logMas, Paths.LOGPATH, true);
        }
        } else {
            String[] logMas = {subject, body};
            WriteWI.Write(logMas, Paths.LOGPATH, true);
        }
    }
}
