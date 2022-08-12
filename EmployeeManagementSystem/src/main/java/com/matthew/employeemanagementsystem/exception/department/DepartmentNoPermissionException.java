package com.matthew.employeemanagementsystem.exception.department;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.nio.file.AccessDeniedException;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class DepartmentNoPermissionException extends AccessDeniedException {
    public DepartmentNoPermissionException(String departmentName) {
        super("You don't have permission to do this in " + departmentName + " department");
    }

    public DepartmentNoPermissionException() {
        super("You don't have permission to do this");
    }
}
