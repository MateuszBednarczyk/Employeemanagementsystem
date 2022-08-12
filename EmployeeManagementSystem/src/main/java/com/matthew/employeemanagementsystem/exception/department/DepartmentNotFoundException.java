package com.matthew.employeemanagementsystem.exception.department;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DepartmentNotFoundException extends IllegalArgumentException {
    public DepartmentNotFoundException(String departmentName) {
        super(departmentName + ", department not found");
    }
}
