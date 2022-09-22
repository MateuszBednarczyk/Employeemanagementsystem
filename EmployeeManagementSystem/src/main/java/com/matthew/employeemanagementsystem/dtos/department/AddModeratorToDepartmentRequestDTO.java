package com.matthew.employeemanagementsystem.dtos.department;

import javax.validation.constraints.NotBlank;

public record AddModeratorToDepartmentRequestDTO(@NotBlank(message = "Username cannot be null") String username,
                                                 @NotBlank(message = "Department name cannot be null") String departmentName) {
}
