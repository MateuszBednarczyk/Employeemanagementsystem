package com.matthew.employeemanagementsystem.repository;

import com.matthew.employeemanagementsystem.domain.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    Optional<EmployeeEntity> findByNameAndSurname(String username, String surname);
}
