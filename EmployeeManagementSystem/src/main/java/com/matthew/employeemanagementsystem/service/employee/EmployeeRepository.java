package com.matthew.employeemanagementsystem.service.employee;

import com.matthew.employeemanagementsystem.domain.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    EmployeeEntity findByName(String username);
}
