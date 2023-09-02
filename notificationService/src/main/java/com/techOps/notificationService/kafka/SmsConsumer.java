package com.techOps.notificationService.kafka;

import com.techOps.notificationService.Dto.SendSmsDto;
import com.techOps.notificationService.services.SendSmsService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SmsConsumer {
    @Autowired
    SendSmsService sendSmsService;

    private static final Logger LOGGER = LoggerFactory.getLogger(SmsConsumer.class);

    @KafkaListener(topics = "sms",groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String smsMessage)  {
        LOGGER.info(String.format("sms message recieved recieved from consumer-> %s", smsMessage));
        SendSmsDto sendSmsDto = new Gson
                ().fromJson(smsMessage, SendSmsDto.class);
        sendSmsService.sendSms(sendSmsDto);

        LOGGER.info("sms message request consumed. ");
    }
}
