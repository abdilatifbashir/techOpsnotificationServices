package com.techOps.notificationService.Dto;


import lombok.Data;

import java.util.List;

@Data
public class SendSmsDto {
    private String apiKey;
    private String message;

    private String accuntid;
    private String from;
    private List<String> to;

}
