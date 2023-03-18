package util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


/**
 *
 */
public class MailUtil {

    public static void sendMail(String to, String ucode){
        Properties properties = new Properties();
        properties.setProperty("mail.debug", "true");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.host", "smtp.sina.com");
        Session session = Session.getInstance(properties,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("wuchengwu5x5@sina.com", "54wangtianyi");
                    }
                });
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("wuchengwu5x5@sina.com"));
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Training College的账号激活");
            message.setContent("<h1>来自Training College注册的激活邮件，激活请点击以下链接</h1>" +
                    "<h3><a href='http://localhost:8080/activate.action?ucode="+ucode+"'>" +
                    "http://localhost:8080/activate.action?ucode="+ucode+"</a></h3>", "text/html;charset=utf-8");
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }
}
