package com.matthew.employeemanagementsystem.dtos.department;

import org.hibernate.annotations.Immutable;

@Immutable
public record DeleteUserEntityFromModeratorListRequestDTO(String username, String departmentName) {
}
