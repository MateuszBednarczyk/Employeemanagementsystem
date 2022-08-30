package com.matthew.employeemanagementsystem.exception;

import com.matthew.employeemanagementsystem.exception.department.DepartmentAlreadyExistsException;
import com.matthew.employeemanagementsystem.exception.department.DepartmentNoPermissionException;
import com.matthew.employeemanagementsystem.exception.department.DepartmentNotFoundException;
import com.matthew.employeemanagementsystem.exception.employee.EmployeeAlreadyExistsException;
import com.matthew.employeemanagementsystem.exception.employee.EmployeeNotFoundException;
import com.matthew.employeemanagementsystem.exception.role.RoleDoesntHavePermissionToThisFeatureException;
import com.matthew.employeemanagementsystem.exception.role.RoleNotFoundException;
import com.matthew.employeemanagementsystem.exception.role.UnexpectedRoleValue;
import com.matthew.employeemanagementsystem.exception.user.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlerController {

    @ExceptionHandler(GeneralException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String generalException(GeneralException e) {
        return e.getMessage();
    }

    @ExceptionHandler(DepartmentAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String departmentAlreadyExistsException(DepartmentAlreadyExistsException e) {
        return e.getMessage();
    }

    @ExceptionHandler(DepartmentNoPermissionException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String departmentNoPermissionException(DepartmentNoPermissionException e) {
        return e.getMessage();
    }

    @ExceptionHandler(DepartmentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String departmentNotFoundException(DepartmentNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(EmployeeAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String employeeAlreadyExistsException(EmployeeAlreadyExistsException e) {
        return e.getMessage();
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String employeeNotFoundException(EmployeeNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(RoleDoesntHavePermissionToThisFeatureException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String roleDoesntHavePermissionToThisFeatureException(RoleDoesntHavePermissionToThisFeatureException e) {
        return e.getMessage();
    }

    @ExceptionHandler(RoleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String roleNotFoundException(RoleNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(UnexpectedRoleValue.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String unexpectedRoleValue(UnexpectedRoleValue e) {
        return e.getMessage();
    }

    @ExceptionHandler(EmptyPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String emptyPasswordException(EmptyPasswordException e) {
        return e.getMessage();
    }

    @ExceptionHandler(UserDoesNotHavePermissionException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String userDoesNotHavePermissionException(UserDoesNotHavePermissionException e) {
        return e.getMessage();
    }

    @ExceptionHandler(UsernameTakenException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String usernameTakenException(UsernameTakenException e) {
        return e.getMessage();
    }

    @ExceptionHandler(PasswordDoesntMatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String passwordDoesntMatchException(PasswordDoesntMatchException e) {
        return e.getMessage();
    }

    @ExceptionHandler(BadRepeatingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String badRepeatingException(BadRepeatingException e) {
        return e.getMessage();
    }

}
