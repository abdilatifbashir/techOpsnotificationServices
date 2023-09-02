package com.techOps.notificationService.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_sms_transactions")
public class SmsTranctions extends BaseEntity{
    private String mobileNumber;
    private String message;
    private String senderId;

    private String accountId;

    private String statusMessage;

    private boolean delivered;

    @Column(name = "AT_message_id")
    private String ATMessageId;


}
