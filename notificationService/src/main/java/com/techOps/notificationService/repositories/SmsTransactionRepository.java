package com.techOps.notificationService.repositories;

import com.techOps.notificationService.entities.SmsTranctions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SmsTransactionRepository extends JpaRepository<SmsTranctions,Long> {
    public Page<SmsTranctions> findSmsTranctionsByAccountId(String accountId, Pageable pageable);
}
