package com.matthew.employeemanagementsystem.exception.role;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.webjars.NotFoundException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoleNotFoundException extends NotFoundException {
    public RoleNotFoundException(String role) {
        super(role + " role not found");
    }
}
