package com.matthew.employeemanagementsystem.dtos.department;

import javax.validation.constraints.NotBlank;

public record AddModeratorToDepartmentRequestDTO(@NotBlank String username,
                                                 @NotBlank String departmentName) {
}
