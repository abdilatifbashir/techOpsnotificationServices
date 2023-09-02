package com.techOps.notificationService.Dto.smsResPayload;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.ArrayList;

@Data
public class SMSMessageData {
    @SerializedName("Message")
    public String message;
    @SerializedName("Recipients")
    public ArrayList<Recipient> recipients;
}
