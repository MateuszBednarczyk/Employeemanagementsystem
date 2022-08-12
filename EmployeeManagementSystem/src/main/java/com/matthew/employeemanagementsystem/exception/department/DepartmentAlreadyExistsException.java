package com.matthew.employeemanagementsystem.exception.department;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DepartmentAlreadyExistsException extends IllegalArgumentException {
    public DepartmentAlreadyExistsException(String departmentName) {
        super(departmentName + ", department already exists");
    }
}
