package com.techOps.notificationService.Dto;

import lombok.Data;

@Data
public class SmsAccountDto {
    private String accountId;
    private String operationType;

    private Integer amount;

}
