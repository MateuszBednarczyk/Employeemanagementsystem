package com.matthew.employeemanagementsystem.mapper;

import com.matthew.employeemanagementsystem.domain.entities.UserEntity;
import com.matthew.employeemanagementsystem.dtos.user.LoginResponseDTO;
import com.matthew.employeemanagementsystem.dtos.user.UserResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserModelMapper {
    UserResponseDTO mapUserEntityToUserResponseDTO(UserEntity userEntity);

    LoginResponseDTO mapUserEntityToLoginResponseDTO(UserEntity userEntity);
}
