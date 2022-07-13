package com.matthew.employeemanagementsystem.service.role;

import com.matthew.employeemanagementsystem.domain.entities.RoleEntity;

import java.rmi.UnexpectedException;

public interface RoleManagementService {
    RoleEntity createRoleEntity(String role) throws UnexpectedException;
}
