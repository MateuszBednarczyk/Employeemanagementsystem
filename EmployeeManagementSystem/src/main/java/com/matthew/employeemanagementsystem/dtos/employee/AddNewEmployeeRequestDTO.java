package com.matthew.employeemanagementsystem.dtos.employee;

import javax.validation.constraints.NotBlank;

public record AddNewEmployeeRequestDTO(@NotBlank(message = "Name cannot be null") String name,
                                       @NotBlank(message = "Surname cannot be null") String surname,
                                       @NotBlank(message = "E-Mail cannot be null") String email,
                                       @NotBlank(message = "Department name cannot be null") String departmentName) {
}
