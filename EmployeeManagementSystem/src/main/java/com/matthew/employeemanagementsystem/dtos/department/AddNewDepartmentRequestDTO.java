package com.matthew.employeemanagementsystem.dtos.department;

import javax.validation.constraints.NotBlank;

public record AddNewDepartmentRequestDTO(@NotBlank(message = "Department name cannot be null") String departmentName) {
}
