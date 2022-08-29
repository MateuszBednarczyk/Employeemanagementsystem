package com.matthew.employeemanagementsystem.dtos.department;

public record ModifyDepartmentRequestDTO(String oldDepartmentName, String repeatOldDepartmentName, String newDepartmentName, String repeatNewDepartmentName) {
}
