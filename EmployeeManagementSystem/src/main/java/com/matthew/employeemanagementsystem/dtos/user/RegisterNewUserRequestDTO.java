package com.matthew.employeemanagementsystem.dtos.user;

public record RegisterNewUserRequestDTO(String username, String password, String email, String department,
                                        String role) {
}
