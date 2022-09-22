package com.matthew.employeemanagementsystem.dtos.employee;

import javax.validation.constraints.NotBlank;

public record ModifyEmployeeRequestDTO(@NotBlank(message = "ID cannot be null") Long id,
                                       @NotBlank(message = "Name cannot be null") String name,
                                       @NotBlank(message = "Surname cannot be null") String surname) {
}
