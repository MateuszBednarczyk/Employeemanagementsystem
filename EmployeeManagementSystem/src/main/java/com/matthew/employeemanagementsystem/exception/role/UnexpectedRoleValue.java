package com.matthew.employeemanagementsystem.exception.role;

public class UnexpectedRoleValue extends IllegalArgumentException {
    public UnexpectedRoleValue(String role){
        super("Unexpected role: " + role);
    }
}
