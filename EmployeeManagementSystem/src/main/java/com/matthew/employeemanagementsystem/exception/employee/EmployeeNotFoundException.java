package com.matthew.employeemanagementsystem.exception.employee;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends IllegalArgumentException {
    public EmployeeNotFoundException(String name, String surname) {
        super("employee " + name + " " + surname + "doesn't exist");
    }
}
