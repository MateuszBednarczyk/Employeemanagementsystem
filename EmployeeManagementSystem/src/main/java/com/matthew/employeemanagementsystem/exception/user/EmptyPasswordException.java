package com.matthew.employeemanagementsystem.exception.user;

public class EmptyPasswordException extends IllegalArgumentException{
    public EmptyPasswordException(){
        super("Password cannot be null");
    }
}
