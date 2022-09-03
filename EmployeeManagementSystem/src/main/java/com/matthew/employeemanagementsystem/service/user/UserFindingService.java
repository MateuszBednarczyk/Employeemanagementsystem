package com.matthew.employeemanagementsystem.service.user;

import com.matthew.employeemanagementsystem.domain.entities.UserEntity;
import com.matthew.employeemanagementsystem.dtos.user.UserResponseDTO;

import java.util.List;

public interface UserFindingService {
    UserEntity getUserEntity(String username);

    List<UserResponseDTO> findUsersByRoleTypeSuperAdmin();
    List<UserResponseDTO> findUsersByRoleTypeAdmin();
    List<UserResponseDTO> findUsersByRoleTypeModerator();
}
