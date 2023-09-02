package com.techOps.notificationService.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Data
@Table(name = "tb_sms_account")
public class SmsAccountEntity extends BaseEntity {
    private String apiKey;
    private String accountName;
    private String accountId;
    private Integer balance;

}
