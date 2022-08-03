package com.matthew.employeemanagementsystem.exception.role;

import org.webjars.NotFoundException;

public class RoleNotFoundException extends NotFoundException {
    public RoleNotFoundException(String role){
        super(role + " role not found");
    }
}
