package com.matthew.employeemanagementsystem.exception.department;

import java.nio.file.AccessDeniedException;

public class DepartmentNoPermissionException extends AccessDeniedException {
    public DepartmentNoPermissionException(String departmentName) {
        super("You don't have permission to do this in " + departmentName + " department");
    }

    public DepartmentNoPermissionException() {
        super("You don't have permission to do this");
    }
}
