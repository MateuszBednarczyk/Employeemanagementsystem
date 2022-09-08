package com.matthew.employeemanagementsystem.dtos.employee;

import javax.validation.constraints.NotBlank;

public record DeleteEmployeeRequestDTO(
        @NotBlank String name,
        @NotBlank String surname,
        @NotBlank String departmentName) {
}
