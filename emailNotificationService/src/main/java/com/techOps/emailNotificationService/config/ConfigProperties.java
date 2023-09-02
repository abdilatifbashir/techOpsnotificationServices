package com.techOps.emailNotificationService.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("sms")
public class ConfigProperties {
    private String username;
    private String apiKey;
    private String endPoint;
}
