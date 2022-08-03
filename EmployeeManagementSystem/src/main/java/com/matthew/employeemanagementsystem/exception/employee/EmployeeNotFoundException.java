package com.matthew.employeemanagementsystem.exception.employee;

public class EmployeeNotFoundException extends IllegalArgumentException {
    public EmployeeNotFoundException(String name, String surname) {
        super("employee " + name + " " + surname + "doesn't exist");
    }
}
