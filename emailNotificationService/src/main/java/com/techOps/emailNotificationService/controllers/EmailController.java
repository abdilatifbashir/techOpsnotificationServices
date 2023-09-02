package com.techOps.emailNotificationService.controllers;

import com.techOps.emailNotificationService.Dto.EmailTemplateDto;
import com.techOps.emailNotificationService.Dto.SendEmailDto;
import com.techOps.emailNotificationService.Dto.UniversalResponse;
import com.techOps.emailNotificationService.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/emails")
public class EmailController {

    @Autowired
    EmailService emailService;



    @PostMapping( "/send")
    public UniversalResponse queueEmail(@RequestBody SendEmailDto sendEmailDto){
        return emailService.queueEmail(sendEmailDto);
    }

    @PostMapping("/create-email-template")

    public UniversalResponse createEmailTemplate(@RequestBody EmailTemplateDto emailTemplateDto){
        return emailService.createEmailTemplate(emailTemplateDto);

    }
}
