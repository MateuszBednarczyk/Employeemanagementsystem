package com.matthew.employeemanagementsystem.exception.role;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnexpectedRoleValue extends IllegalArgumentException {
    public UnexpectedRoleValue(String role) {
        super("Unexpected role: " + role);
    }
}
