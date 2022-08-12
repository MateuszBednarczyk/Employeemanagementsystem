package com.matthew.employeemanagementsystem.dtos.department;

import com.matthew.employeemanagementsystem.domain.entities.EmployeeEntity;
import org.hibernate.annotations.Immutable;

import java.util.List;

@Immutable
public record DepartmentResponseDTO(String departmentName, List<EmployeeEntity> employeesList) {
}
