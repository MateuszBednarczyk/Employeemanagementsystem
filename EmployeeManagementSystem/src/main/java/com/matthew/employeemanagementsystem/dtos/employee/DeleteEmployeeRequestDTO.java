package com.matthew.employeemanagementsystem.dtos.employee;

import javax.validation.constraints.NotBlank;

public record DeleteEmployeeRequestDTO(@NotBlank(message = "Name cannot be null") String name,
                                       @NotBlank(message = "Surname cannot be null") String surname,
                                       @NotBlank(message = "Department name cannot be null") String departmentName) {
}
