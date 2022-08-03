package com.matthew.employeemanagementsystem.exception.employee;

public class EmployeeAlreadyExistsException extends IllegalArgumentException{
    public EmployeeAlreadyExistsException(String name, String surname, String departmentName){
        super(name + " " + surname + " already exists in " + departmentName + " department");
    }
}
