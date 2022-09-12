package com.matthew.employeemanagementsystem.repository;

import com.matthew.employeemanagementsystem.domain.entities.VerificationTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationTokenEntity, Long> {
    VerificationTokenEntity findByTokenValue(String tokenValue);

    void deleteByUserId(Long id);
}
