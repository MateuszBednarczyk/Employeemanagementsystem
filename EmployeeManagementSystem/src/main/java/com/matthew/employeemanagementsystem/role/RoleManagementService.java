package com.matthew.employeemanagementsystem.role;

import com.matthew.employeemanagementsystem.domain.entities.RoleEntity;

import java.rmi.UnexpectedException;

public interface RoleManagementService {
    RoleEntity createRoleEntity(String role) throws UnexpectedException;
}
