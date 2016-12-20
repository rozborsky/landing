package ua.rozborsky.classes;

/**
 * Created by roman on 24.07.2016.
 */

import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

@Component
public class SendLetter {

    private String adress;
    private String goalAdress;
    private String subject;
    private String password;

    public void setParameters(String adress, String password, String goalAdress, String subject) {
        this.adress = adress;
        this.password = password;
        this.goalAdress = goalAdress;
        this.subject = subject;
    }

    public void send(){

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(adress, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(goalAdress));
            message.setSubject(subject);
            message.setText(setMessage());
            message.setContent(addImage());

            Transport.send(message);
        } catch (MessagingException e) {
           e.printStackTrace();
        }
    }

    private static MimeMultipart addImage() throws MessagingException {
        MimeMultipart multipart = new MimeMultipart();
        BodyPart messageBodyPart = new MimeBodyPart();
        DataSource dataSource = new FileDataSource(chooseImage());
        messageBodyPart.setDataHandler(new DataHandler(dataSource));
        multipart.addBodyPart(messageBodyPart);
        return multipart;
    }

    private static String chooseImage() {
        return "D:/Rozborsky.pdf";
    } //D:/images/.......jpg

    private static String setMessage() {
        return "message";
    }
}