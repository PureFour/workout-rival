package com.ruczajsoftware.workoutrival.service;

import com.ruczajsoftware.workoutrival.config.EmailConfiguration;
import com.ruczajsoftware.workoutrival.model.email.EmailTemplate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailService {

    private EmailConfiguration configuration;

    public void sendTextEmail(EmailTemplate emailTemplate) throws MessagingException {
        Session session = configuration.getSession();

        Transport transport = session.getTransport();
        InternetAddress addressFrom = new InternetAddress(configuration.getUsername());

        MimeMessage message = new MimeMessage(session);
        message.setSender(addressFrom);
        message.setSubject(emailTemplate.getSubject());
        message.setContent(emailTemplate.getPayload(), "text/plain");
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTemplate.getEmail()));

        transport.connect();
        Transport.send(message);
        transport.close();
    }
}
