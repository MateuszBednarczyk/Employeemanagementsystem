package com.matthew.employeemanagementsystem.repository;

import com.matthew.employeemanagementsystem.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

    void deleteByUsername(String username);
}
