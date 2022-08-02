package com.matthew.employeemanagementsystem.service.role;

import com.matthew.employeemanagementsystem.domain.entities.RoleEntity;
import com.matthew.employeemanagementsystem.domain.types.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class RoleFacadeImpl implements RoleFacade {

    private final RoleFindingService roleFindingService;
    private final RoleManagementService roleManagementService;

    @Override
    public RoleEntity createRoleEntity(String role) throws IllegalArgumentException {
        return roleManagementService.createRoleEntity(role);
    }

    @Override
    public void deleteRoleEntity(String role) throws IllegalArgumentException {
        roleManagementService.deleteRoleEntity(role);
    }

    @Override
    public RoleEntity findByRoleType(RoleType roleType) {
        return roleFindingService.findByRoleType(roleType);
    }
}
