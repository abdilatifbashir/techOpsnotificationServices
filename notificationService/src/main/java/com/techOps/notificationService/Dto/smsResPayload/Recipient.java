package com.techOps.notificationService.Dto.smsResPayload;

import lombok.Data;

@Data
public class Recipient {

    public String number;
    public String cost;
    public int messageParts;
    public String messageId;
    public String status;
    public int statusCode;

}
