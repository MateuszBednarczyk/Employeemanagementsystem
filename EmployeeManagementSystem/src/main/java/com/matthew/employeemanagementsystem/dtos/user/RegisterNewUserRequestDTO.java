package com.matthew.employeemanagementsystem.dtos.user;

public record RegisterNewUserRequestDTO(String username, String password, String department, String role) {
}
