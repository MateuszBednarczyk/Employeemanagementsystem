package com.matthew.employeemanagementsystem.dtos.department;

import org.hibernate.annotations.Immutable;

@Immutable
public record AddModeratorToDepartmentRequestDTO(String username, String departmentName) {
}
