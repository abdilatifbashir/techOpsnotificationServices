package com.techOps.notificationService.kafka;

import com.techOps.notificationService.Dto.SendEmailDto;
import com.techOps.notificationService.services.SendEmailService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmailConsumer {
    @Autowired
    SendEmailService sendEmailService;


    private static final Logger LOGGER = LoggerFactory.getLogger(SmsConsumer.class);

    @KafkaListener(topics = "email",groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String emailMessage)  {
        LOGGER.info(String.format("email message recieved recieved from consumer-> %s", emailMessage));
        SendEmailDto sendEmailDto = new Gson
                ().fromJson(emailMessage, SendEmailDto.class);
        sendEmailService.sendSimpleEmail(sendEmailDto);

        LOGGER.info("email message request consumed. ");
    }
}
