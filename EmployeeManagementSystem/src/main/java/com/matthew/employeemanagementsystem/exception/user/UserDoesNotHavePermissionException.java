package com.matthew.employeemanagementsystem.exception.user;

import org.springframework.security.access.AccessDeniedException;

public class UserDoesNotHavePermissionException extends AccessDeniedException {
    public UserDoesNotHavePermissionException(String username) {
        super("Sorry " + username + " you don't have permission to do this");
    }
}
