package com.techOps.notificationService.repositories;

import com.techOps.notificationService.entities.SmsSenderDetailsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SmsSenderDetailsRepository extends JpaRepository<SmsSenderDetailsEntity,Long> {
   Optional<SmsSenderDetailsEntity> findBySenderId(String senderId);
    Page<SmsSenderDetailsEntity> findSmsSenderDetailsEntityByAccountId(String accountId, Pageable pageable);

}
