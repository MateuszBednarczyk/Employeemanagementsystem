package com.matthew.employeemanagementsystem.exception.employee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmployeeAlreadyExistsException extends IllegalArgumentException {
    public EmployeeAlreadyExistsException(String name, String surname, String departmentName) {
        super(name + " " + surname + " already exists in " + departmentName + " department");
    }
}
