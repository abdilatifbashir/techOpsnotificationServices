package com.techOps.notificationService.Dto;

import lombok.Data;

@Data
public class SendEmailDto {
    private String to;
    private String name;
    private String subject;
    private String message;

}
