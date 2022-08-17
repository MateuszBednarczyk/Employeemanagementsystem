package com.matthew.employeemanagementsystem.dtos.employee;

import com.matthew.employeemanagementsystem.domain.entities.DepartmentEntity;

import java.util.List;

public record EmployeeResponseDTO(String name, String surname, List<DepartmentEntity> departmentEntities) {
}
