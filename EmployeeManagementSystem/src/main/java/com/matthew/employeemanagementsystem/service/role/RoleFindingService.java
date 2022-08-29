package com.matthew.employeemanagementsystem.service.role;

import com.matthew.employeemanagementsystem.domain.entities.RoleEntity;
import com.matthew.employeemanagementsystem.domain.enums.RoleType;

interface RoleFindingService {
    RoleEntity findByRoleType(RoleType roleList);
}
