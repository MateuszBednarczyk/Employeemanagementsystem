package com.matthew.employeemanagementsystem.dtos.user;

import org.hibernate.annotations.Immutable;

@Immutable
public record RegisterNewUserRequestDTO(String username, String password, String department, String role) {
}
