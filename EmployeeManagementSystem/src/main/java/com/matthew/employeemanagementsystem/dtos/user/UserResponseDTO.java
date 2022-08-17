package com.matthew.employeemanagementsystem.dtos.user;

import com.matthew.employeemanagementsystem.domain.entities.DepartmentEntity;
import com.matthew.employeemanagementsystem.domain.entities.RoleEntity;

import java.util.List;

public record UserResponseDTO(String username, List<DepartmentEntity> departmentEntities, List<RoleEntity> roles) {
}
