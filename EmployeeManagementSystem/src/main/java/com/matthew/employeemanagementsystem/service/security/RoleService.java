package com.matthew.employeemanagementsystem.service.security;

import com.matthew.employeemanagementsystem.domain.entities.UserEntity;

public interface RoleService {
    boolean isUserSuperAdmin(UserEntity user);

    boolean isUserAdmin(UserEntity user);

    boolean isUserSuperAdminOrAdmin(UserEntity user);

    boolean isGivenRoleSuperAdminOrAdmin(String role);
}
