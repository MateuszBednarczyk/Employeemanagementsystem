package com.matthew.employeemanagementsystem.dtos.user;

import com.matthew.employeemanagementsystem.domain.entities.DepartmentEntity;
import com.matthew.employeemanagementsystem.domain.entities.RoleEntity;
import org.hibernate.annotations.Immutable;

import java.util.List;

@Immutable
public record UserResponseDTO(String username, List<DepartmentEntity> departmentList, List<RoleEntity> roles) {
}
