package com.matthew.employeemanagementsystem.service.role;

import com.matthew.employeemanagementsystem.domain.entities.RoleEntity;
import com.matthew.employeemanagementsystem.domain.types.RoleType;

public interface RoleFacade {
    RoleEntity createRoleEntity(String role);

    void deleteRoleEntity(String role);

    RoleEntity findByRoleType(RoleType roleType);

}
