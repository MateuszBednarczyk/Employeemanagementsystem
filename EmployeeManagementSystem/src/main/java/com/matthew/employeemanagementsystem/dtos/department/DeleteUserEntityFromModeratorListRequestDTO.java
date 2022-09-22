package com.matthew.employeemanagementsystem.dtos.department;

import javax.validation.constraints.NotBlank;

public record DeleteUserEntityFromModeratorListRequestDTO(
        @NotBlank(message = "Username cannot be null") String username,
        @NotBlank(message = "Department name cannot be null") String departmentName) {
}
