package com.matthew.employeemanagementsystem.dtos.employee;

import com.matthew.employeemanagementsystem.domain.entities.DepartmentEntity;

import java.util.List;

public record EmployeeResponseDTO(Long id, String name, String surname, String email, List<DepartmentEntity> departmentEntities) {
}
