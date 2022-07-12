package com.matthew.employeemanagementsystem.user.dtos;

public record RegisterNewUserRequestDTO(String username, String password, String department, String role) {
}
