package com.matthew.employeemanagementsystem.service.department;

import com.matthew.employeemanagementsystem.domain.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
    Optional<DepartmentEntity> findByDepartment(String department);
}
