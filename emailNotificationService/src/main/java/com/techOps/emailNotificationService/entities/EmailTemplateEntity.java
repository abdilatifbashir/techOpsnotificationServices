package com.techOps.emailNotificationService.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tbl_email_templates")
public class EmailTemplateEntity extends BaseEntity{

    @Column(name = "template_code")
    private String templateCode;

    @Column(name = "template_name")
    private String templateName;

    @Column(name = "template_message")
    private String templateMessage;
}
