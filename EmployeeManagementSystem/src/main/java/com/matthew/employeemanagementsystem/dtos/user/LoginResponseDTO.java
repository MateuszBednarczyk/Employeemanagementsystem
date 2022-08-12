package com.matthew.employeemanagementsystem.dtos.user;

import org.hibernate.annotations.Immutable;

@Immutable
public record LoginResponseDTO(String username) {
}
