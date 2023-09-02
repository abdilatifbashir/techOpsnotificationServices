package com.techOps.emailNotificationService.kafka;

import com.techOps.emailNotificationService.Dto.SendEmailDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KafkaEmailProducer {
    // @Value("${spring.kafka.topic-json.name}")
// private String topicJsonName;

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaEmailProducer.class);

    private KafkaTemplate<String, SendEmailDto> kafkaTemplate;

    public KafkaEmailProducer(KafkaTemplate<String, SendEmailDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(SendEmailDto data){

        LOGGER.info(String.format("Message sent -> %s", data.toString()));

        Message<SendEmailDto> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, "email")
                .build();

        kafkaTemplate.send(message);
    }
}
