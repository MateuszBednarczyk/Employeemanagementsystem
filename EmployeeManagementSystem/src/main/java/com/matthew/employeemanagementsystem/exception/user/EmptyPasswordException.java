package com.matthew.employeemanagementsystem.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmptyPasswordException extends IllegalArgumentException {
    public EmptyPasswordException() {
        super("Password cannot be null");
    }
}
