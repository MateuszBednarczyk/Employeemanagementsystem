package com.matthew.employeemanagementsystem.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserIsAlredadyModeratorInDepartment extends IllegalArgumentException {
    public UserIsAlredadyModeratorInDepartment(String username, String departmentName) {
        super(username + " is already an moderator in " + departmentName + " department");
    }
}
