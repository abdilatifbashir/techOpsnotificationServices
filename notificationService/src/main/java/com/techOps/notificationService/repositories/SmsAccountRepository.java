package com.techOps.notificationService.repositories;

import com.techOps.notificationService.entities.SmsAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SmsAccountRepository extends JpaRepository<SmsAccountEntity,Long> {
    Optional<SmsAccountEntity> findByEwarrantyAccountId(String accountId);
    Optional<SmsAccountEntity> findByApiKeyAndAccountId(String apiKey, String accountId);
    Optional<SmsAccountEntity> findSmsAccountEntitiesByAccountName(String accountName);

}
