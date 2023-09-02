package com.techOps.notificationService.Dto;


import lombok.Data;

@Data
public class SmsSenderDto {
    private String accountName;
    private String senderId;
    private String username;
    private String password;
    private String sendEndPoint;
    private String accountId;
    private String providerName;
    private String providerCode;
}
