package com.matthew.employeemanagementsystem.dtos.department;

import javax.validation.constraints.NotBlank;

public record ModifyDepartmentRequestDTO(@NotBlank(message = "Old name cannot be null") String oldDepartmentName,
                                         @NotBlank(message = "Old name repeating cannot be null") String repeatOldDepartmentName,
                                         @NotBlank(message = "New department name cannot be null") String newDepartmentName,
                                         @NotBlank(message = "New department name repeating cannot be null") String repeatNewDepartmentName) {
}
