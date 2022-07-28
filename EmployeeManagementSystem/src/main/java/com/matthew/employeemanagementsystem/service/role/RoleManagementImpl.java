package com.matthew.employeemanagementsystem.service.role;

import com.matthew.employeemanagementsystem.domain.entities.RoleEntity;
import com.matthew.employeemanagementsystem.domain.types.RoleType;
import com.matthew.employeemanagementsystem.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class RoleManagementImpl implements RoleManagementService {

    private final RoleRepository roleRepository;

    @Override
    public RoleEntity createRoleEntity(String role) {
        RoleEntity roleEntity = new RoleEntity();
        if (role.equals("ROLE_ADMIN") || role.equals("ROLE_MODERATOR")) {
            roleEntity.setRoleType(RoleType.valueOf(role));
            roleRepository.save(roleEntity);
        } else {
            throw new IllegalArgumentException("Unexpected role");
        }

        return roleEntity;
    }
}
