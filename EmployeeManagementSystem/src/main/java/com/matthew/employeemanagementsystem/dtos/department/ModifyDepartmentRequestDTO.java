package com.matthew.employeemanagementsystem.dtos.department;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record ModifyDepartmentRequestDTO(
        @NotBlank String oldDepartmentName,
        @NotBlank String repeatOldDepartmentName,
        @NotBlank @Size(min = 2, max = 25, message = "Department name has to be between 2 and 25 characters") String newDepartmentName,
        @NotBlank @Size(min = 2, max = 25, message = "Department name has to be between 2 and 25 characters") String repeatNewDepartmentName) {
}
