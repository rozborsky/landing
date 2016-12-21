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

    private String address;
    private String goalAddress;
    private String subject = "CV ";
    private String password;

    public void setParameters(String adress, String password, String goalAddress, String name) {
        this.address = adress;
        this.password = password;
        this.goalAddress = goalAddress;
        this.subject += name;
    }

    public void send(String remarks, String cvName, String dirPath){

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(address, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(address));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(goalAddress));
            message.setSubject(subject);

            BodyPart text = new MimeBodyPart();
            text.setText(remarks);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(text);


            BodyPart cv = new MimeBodyPart();
            DataSource dataSource = new FileDataSource(dirPath + cvName);
            cv.setDataHandler(new DataHandler(dataSource));
            cv.setFileName(cvName);

            multipart.addBodyPart(cv);

            message.setContent(multipart);
            Transport.send(message);
        } catch (MessagingException e) {
           e.printStackTrace();
        }
    }
}