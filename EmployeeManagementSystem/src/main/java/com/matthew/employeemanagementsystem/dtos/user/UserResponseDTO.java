package com.matthew.employeemanagementsystem.dtos.user;

import com.matthew.employeemanagementsystem.domain.entities.DepartmentEntity;
import com.matthew.employeemanagementsystem.domain.enums.RoleType;

import java.util.List;

public record UserResponseDTO(String username, String email, List<DepartmentEntity> departmentEntities,
                              RoleType role) {
}
