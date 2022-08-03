package com.matthew.employeemanagementsystem.service.role;

import com.matthew.employeemanagementsystem.domain.entities.RoleEntity;
import com.matthew.employeemanagementsystem.domain.types.RoleType;
import com.matthew.employeemanagementsystem.exception.role.RoleNotFoundException;
import com.matthew.employeemanagementsystem.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class RoleFindingServiceImpl implements RoleFindingService {

    private final RoleRepository roleRepository;

    @Override
    public RoleEntity findByRoleType(RoleType roleType) {
        return roleRepository.findByRoleType(roleType).orElseThrow(() -> new RoleNotFoundException(String.valueOf(roleType)));
    }
}
