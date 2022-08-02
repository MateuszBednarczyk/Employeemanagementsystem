package com.matthew.employeemanagementsystem.service.role;

import com.matthew.employeemanagementsystem.domain.entities.RoleEntity;
import com.matthew.employeemanagementsystem.domain.types.RoleType;

public interface RoleFacade {
    RoleEntity createRoleEntity(String role) throws IllegalArgumentException;

    void deleteRoleEntity(String role) throws IllegalArgumentException;

    RoleEntity findByRoleType(RoleType roleType);

}
