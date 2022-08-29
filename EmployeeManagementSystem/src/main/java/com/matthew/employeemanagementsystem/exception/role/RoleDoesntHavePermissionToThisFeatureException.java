package com.matthew.employeemanagementsystem.exception.role;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class RoleDoesntHavePermissionToThisFeatureException extends AccessDeniedException {
    public RoleDoesntHavePermissionToThisFeatureException() {
        super("you don't have permission");
    }
}
