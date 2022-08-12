package com.matthew.employeemanagementsystem.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UsernameTakenException extends IllegalArgumentException {
    public UsernameTakenException(String username) {
        super(username + " username is already taken");
    }
}
