package com.matthew.employeemanagementsystem.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserDoesNotHavePermissionException extends AccessDeniedException {
    public UserDoesNotHavePermissionException(String username) {
        super("Sorry " + username + " you don't have permission to do this");
    }
}
