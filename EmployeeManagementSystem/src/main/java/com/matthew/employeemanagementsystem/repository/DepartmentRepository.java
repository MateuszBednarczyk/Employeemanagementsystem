package com.matthew.employeemanagementsystem.repository;

import com.matthew.employeemanagementsystem.domain.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
    Optional<DepartmentEntity> findByDepartmentName(String departmentName);

    void deleteByDepartmentName(String departmentName);

    List<DepartmentEntity> findAll();
}
