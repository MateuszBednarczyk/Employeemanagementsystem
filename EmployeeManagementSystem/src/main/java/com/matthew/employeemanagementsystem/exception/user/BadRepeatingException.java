package com.matthew.employeemanagementsystem.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRepeatingException extends IllegalArgumentException {
    public BadRepeatingException() {
        super("Bad repeating");
    }
}
