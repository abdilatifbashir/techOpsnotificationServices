package com.techOps.emailNotificationService.services;

import com.techOps.emailNotificationService.Dto.EmailTemplateDto;
import com.techOps.emailNotificationService.Dto.SendEmailDto;
import com.techOps.emailNotificationService.Dto.UniversalResponse;
import com.techOps.emailNotificationService.entities.EmailTemplateEntity;
import com.techOps.emailNotificationService.kafka.KafkaEmailProducer;
import com.techOps.emailNotificationService.repositories.EmailTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmailService {

    @Autowired
    private KafkaEmailProducer kafkaEmailProducer;
    @Autowired
    EmailTemplateRepository emailTemplateRepository;


    public UniversalResponse queueEmail(SendEmailDto sendEmailDto){
        if(sendEmailDto.getTemplateCode().equals("null")){
            kafkaEmailProducer.sendMessage(sendEmailDto);
            return new UniversalResponse(200,"message queued successfullly",sendEmailDto);


        } else {
            Optional<EmailTemplateEntity> template = emailTemplateRepository.findByTemplateCode(sendEmailDto.getTemplateCode());
            if(template.isPresent()){
                sendEmailDto.setMessage(template.get().getTemplateMessage());
                kafkaEmailProducer.sendMessage(sendEmailDto);
                return new UniversalResponse(200,"message template queued successfullly",sendEmailDto);


            }
            if(!template.isPresent()){
                return new UniversalResponse(400,"template does not exist,check your template code ");


            }

        }



        return new UniversalResponse(400,"Bad Request");
    }

    public UniversalResponse createEmailTemplate(EmailTemplateDto emailTemplateDto){
        Optional<EmailTemplateEntity> emailTemplate = emailTemplateRepository.findByTemplateCode(emailTemplateDto.getTemplateCode());
        if(emailTemplate.isPresent()){
            return new UniversalResponse(400,"template code already exist",emailTemplateDto);

        }

        EmailTemplateEntity emailTemplateEntity = new EmailTemplateEntity();
        emailTemplateEntity.setTemplateCode(emailTemplateDto.getTemplateCode());
        emailTemplateEntity.setTemplateName(emailTemplateDto.getTemplateName());
        emailTemplateEntity.setTemplateMessage(emailTemplateDto.getTemplateMessage());
        emailTemplateRepository.save(emailTemplateEntity);

        return new UniversalResponse(200,"template created successfullly",emailTemplateEntity);


    }
}
