package com.matthew.employeemanagementsystem.service.role;

import com.matthew.employeemanagementsystem.domain.entities.RoleEntity;

interface RoleManagementService {
    RoleEntity createRoleEntity(String role) throws IllegalArgumentException;

    void deleteRoleEntity(String role) throws IllegalArgumentException;
}
