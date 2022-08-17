package com.matthew.employeemanagementsystem.dtos.employee;

import com.matthew.employeemanagementsystem.domain.entities.DepartmentEntity;
import org.hibernate.annotations.Immutable;

import java.util.List;

@Immutable
public record EmployeeResponseDTO(String name, String surname, List<DepartmentEntity> departmentEntities) {
}
