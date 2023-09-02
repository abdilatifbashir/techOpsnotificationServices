package com.techOps.emailNotificationService.Dto;

import lombok.Data;
import org.w3c.dom.Text;

@Data
public class SendEmailDto {
    private String to;
    private String name;
    private String subject;
    private String message;

    private String templateCode;

}
