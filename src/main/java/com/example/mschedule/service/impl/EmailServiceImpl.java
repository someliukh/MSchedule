package com.example.mschedule.service.impl;

import com.example.mschedule.constant.GmailConstants;
import com.example.mschedule.entity.User;
import com.example.mschedule.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Override
    public void sendMessageWithPassword(User user, String password) {
        Properties properties = setUpProperties();
        Session session = setUpSession(properties);

        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(GmailConstants.USERNAME));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(user.getEmail()));
            message.setSubject("Password for " + user.getFirstname() + " " + user.getLastname() + " for MSchedule service");
            message.setText(GmailConstants.MESSAGE + password);

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private Properties setUpProperties() {
        Properties properties = new Properties();

        properties.put("mail.smtp.host", GmailConstants.SMTP);
        properties.put("mail.smtp.port", GmailConstants.PORT);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        return properties;
    }

    private Session setUpSession(Properties properties) {
        return Session.getInstance(properties,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(GmailConstants.USERNAME, GmailConstants.PASSWORD);
                    }
                });
    }

}
