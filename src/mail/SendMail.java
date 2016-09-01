package mail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
/**
 * @author Svitlyk
 */
public class SendMail {

    private static final String FROM = "ngnservise@mail.ru";
    private static final String SMTPHOST = "smtp.mail.ru";
    private static final String PORT = "2525";
    private static final String LOGIN = "ngnservise@mail.ru";
    private static final String PASSWORD = "NgnAdmin2016";
    private static final String TO = "svitlyk.oleksandr@mail.ru";
    private static Session mailSession;
    private static MimeMessage message;
    private static Multipart mp;
    private static MimeBodyPart mbp;

    public static void sendEmail(String body, String subject, String recipient) throws MessagingException, UnsupportedEncodingException {

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
        message.setFrom(new InternetAddress(FROM));
        String[] emails = {recipient};
        InternetAddress dests[] = new InternetAddress[emails.length];
        for (int i = 0; i < emails.length; i++) {
            dests[i] = new InternetAddress(emails[i].trim().toLowerCase());
        }
        message.setRecipients(Message.RecipientType.TO, dests);
        message.setSubject(subject, "UTF-8");
        mp = new MimeMultipart();
        mbp = new MimeBodyPart();
        mbp.setContent(body, "text/html;charset=utf-8");
        mp.addBodyPart(mbp);
        message.setContent(mp);
        message.setSentDate(new java.util.Date());

        Transport.send(message);
    }
}