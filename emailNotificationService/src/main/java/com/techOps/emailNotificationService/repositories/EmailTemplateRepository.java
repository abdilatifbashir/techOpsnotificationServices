package com.techOps.emailNotificationService.repositories;

import com.techOps.emailNotificationService.entities.EmailTemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailTemplateRepository extends JpaRepository<EmailTemplateEntity,Long> {
   public Optional<EmailTemplateEntity> findByTemplateCode(String code);
}
