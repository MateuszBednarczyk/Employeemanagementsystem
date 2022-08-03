package com.matthew.employeemanagementsystem.exception.user;

public class UsernameTakenException extends IllegalArgumentException {
    public UsernameTakenException(String username) {
        super(username + " username is already taken");
    }
}
