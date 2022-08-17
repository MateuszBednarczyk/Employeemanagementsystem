package com.matthew.employeemanagementsystem.service.role;

import com.matthew.employeemanagementsystem.domain.entities.RoleEntity;
import com.matthew.employeemanagementsystem.domain.types.RoleType;
import com.matthew.employeemanagementsystem.exception.role.RoleNotFoundException;
import com.matthew.employeemanagementsystem.exception.role.UnexpectedRoleValue;
import com.matthew.employeemanagementsystem.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
class RoleManagementServiceImpl implements RoleManagementService {

    private final RoleRepository roleRepository;

    @Override
    public RoleEntity createRoleEntity(String role) {
        RoleEntity roleEntity = new RoleEntity();
        if (role.equals("ROLE_ADMIN") || role.equals("ROLE_MODERATOR")) {
            roleEntity.setRoleType(RoleType.valueOf(role));
            roleRepository.save(roleEntity);
        } else {
            throw new UnexpectedRoleValue(role);
        }

        return roleEntity;
    }

    @Override
    public void deleteRoleEntity(String role) {
        RoleEntity roleEntity = roleRepository.findByRoleType(RoleType.valueOf(role)).orElseThrow(() -> new RoleNotFoundException(role));
        roleRepository.deleteByRoleType(roleEntity.getRoleType());
    }
}
