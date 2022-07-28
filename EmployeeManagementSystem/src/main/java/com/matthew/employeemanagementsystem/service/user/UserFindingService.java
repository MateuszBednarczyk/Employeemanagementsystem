package com.matthew.employeemanagementsystem.service.user;

import com.matthew.employeemanagementsystem.domain.entities.UserEntity;

public interface UserFindingService {
    UserEntity getUserEntity(String username);
}
