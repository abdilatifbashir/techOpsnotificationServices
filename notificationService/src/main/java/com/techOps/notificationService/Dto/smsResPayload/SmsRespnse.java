package com.techOps.notificationService.Dto.smsResPayload;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class SmsRespnse {
    @SerializedName("SMSMessageData")
    public SMSMessageData sMSMessageData;
}
