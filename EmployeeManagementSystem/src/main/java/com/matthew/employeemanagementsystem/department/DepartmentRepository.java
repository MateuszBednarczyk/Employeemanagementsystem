package com.matthew.employeemanagementsystem.department;

import com.matthew.employeemanagementsystem.domain.entities.DepartmentEntity;
import com.matthew.employeemanagementsystem.domain.types.DepartmentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
    Optional<DepartmentEntity> findByDepartmentType(DepartmentType departmentType);
}
