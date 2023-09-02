package com.techOps.notificationService.services;

import com.techOps.notificationService.Dto.SendEmailDto;
import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")

    private String username;



    public void sendSimpleEmail(SendEmailDto sendEmailDto
                                ) {
//        MimeMessage message = emailSender.createMimeMessage();
//        message.setFrom(username);
//        message.setTo(toEmail);
//        message.setText(body);
//        message.setSubject(subject);
//        mailSender.send(message);

        MimeMessage message = mailSender.createMimeMessage();

        try {
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(sendEmailDto.getTo()));

            message.setSubject(sendEmailDto.getSubject());
            message.setContent(sendEmailDto.getMessage(),"text/html");


//            LOGGER.info(String.format("Sending mail %n%s%n%s%n to %s ",subject,body,toEmail));
            mailSender.send(message);
        } catch (Exception ex) {
//            LOGGER.error(ex)
        }


    }
}
