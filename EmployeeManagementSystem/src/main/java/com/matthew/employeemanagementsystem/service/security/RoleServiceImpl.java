package com.matthew.employeemanagementsystem.service.security;

import com.matthew.employeemanagementsystem.domain.entities.UserEntity;
import com.matthew.employeemanagementsystem.domain.enums.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class RoleServiceImpl implements RoleService {
    @Override
    public boolean isUserSuperAdmin(UserEntity user) {
        return user.getRole().equals(RoleType.ROLE_SUPERADMIN);
    }

    @Override
    public boolean isUserAdmin(UserEntity user) {
        return user.getRole().equals(RoleType.ROLE_ADMIN);
    }

    @Override
    public boolean isUserSuperAdminOrAdmin(UserEntity user) {
        return user.getRole().equals(RoleType.ROLE_SUPERADMIN) || user.getRole().equals(RoleType.ROLE_ADMIN);
    }

    @Override
    public boolean isGivenRoleSuperAdminOrAdmin(String role) {
        return role.contains("SUPERADMIN") || role.contains("ADMIN");
    }
}
