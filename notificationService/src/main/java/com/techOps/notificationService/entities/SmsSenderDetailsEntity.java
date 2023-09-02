package com.techOps.notificationService.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_sms_sender_details")
public class SmsSenderDetailsEntity extends BaseEntity {
    @Column(name = "account_name")
    private String accountName;

    private String senderId;
    private String username;

    private String password;

    @Column(name = "send_sms_endpoint")
    private String sendEndPoint;

    private String accountId;

    private String providerName;

    private String providerCode;



}
