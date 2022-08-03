package com.matthew.employeemanagementsystem.exception.department;

public class DepartmentAlreadyExistsException extends IllegalArgumentException{
    public DepartmentAlreadyExistsException(String departmentName){
        super(departmentName + ", department already exists");
    }
}
