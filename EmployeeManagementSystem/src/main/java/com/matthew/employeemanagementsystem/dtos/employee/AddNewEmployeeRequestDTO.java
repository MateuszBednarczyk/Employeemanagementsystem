package com.matthew.employeemanagementsystem.dtos.employee;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record AddNewEmployeeRequestDTO(
        @NotBlank @Size(min = 1, max = 50, message = "Name has to be between 1 and 50 characters") String name,
        @NotBlank @Size(min = 1, max = 60, message = "Surname has to be between 1 and 50 characters") String surname,
        @NotBlank @Size(min = 1, max = 100, message = "Email has to be between 1 and 100 characters") String email,
        @NotBlank String departmentName) {
}
