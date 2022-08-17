package com.matthew.employeemanagementsystem.dtos.department;

import com.matthew.employeemanagementsystem.domain.entities.EmployeeEntity;

import java.util.List;

public record DepartmentResponseDTO(String departmentName, List<EmployeeEntity> employeesList) {
}
