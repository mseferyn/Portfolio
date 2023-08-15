package pl.ug.SateliteApp.SateliteApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.*;
import org.springframework.stereotype.Service;
import pl.ug.SateliteApp.SateliteApp.util.SmtpAuthenticator;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

@Service
public class MailService {

    public SmtpAuthenticator authenticator = new SmtpAuthenticator();
    public static final String PASSWORD = "mail.password";
    public static final String USER = "mail.user";
    public static final String SMTP_HOST = "mail.smtp.host";
    public static final String TRANSPORT_PROTOCOL = "mail.transport.protocol";
    public static final String AUTH = "mail.smtp.auth";

    public void send(String from, String to, String cc, String bcc, String subject, String body, List<File> attachments) throws MessagingException, java.io.IOException {
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.transport.protocol", "smtp");
        prop.put("mail.password", "lzuuuzhfjsokhovt");
        prop.put("mail.user", "satellitesbachelor@gmail.com");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");


        Session session = Session.getDefaultInstance(prop, authenticator);
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(from));
        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        msg.setSubject(subject);

        msg.setText(body);

        msg.saveChanges();
        if (Boolean.parseBoolean(prop.getProperty(AUTH))) {
            Transport transport = session.getTransport(prop.getProperty(TRANSPORT_PROTOCOL));
            transport.connect(prop.getProperty(SMTP_HOST), "satellitesbachelor@gmail.com", "lzuuuzhfjsokhovt");
            transport.send(msg);
            transport.close();
        } else {
            Transport.send(msg);
        }
    }
}