package com.techOps.emailNotificationService.Dto;

import lombok.Data;

@Data
public class EmailTemplateDto {
    private String templateCode;
    private String templateName;
    private String templateMessage;
}
