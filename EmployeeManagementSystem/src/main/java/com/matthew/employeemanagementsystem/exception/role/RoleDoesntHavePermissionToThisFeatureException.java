package com.matthew.employeemanagementsystem.exception.role;

import org.springframework.security.access.AccessDeniedException;

public class RoleDoesntHavePermissionToThisFeatureException extends AccessDeniedException {
    public RoleDoesntHavePermissionToThisFeatureException(String role) {
        super("you can't do this as " + role);
    }
}
