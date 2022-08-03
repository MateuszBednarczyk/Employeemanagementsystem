package com.matthew.employeemanagementsystem.exception.department;

public class DepartmentNotFoundException extends IllegalArgumentException {
    public DepartmentNotFoundException(String departmentName) {
        super(departmentName + ", department not found");
    }
}
