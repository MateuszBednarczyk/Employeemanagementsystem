package com.matthew.employeemanagementsystem.mapper;

import com.matthew.employeemanagementsystem.domain.entities.UserEntity;
import com.matthew.employeemanagementsystem.dtos.user.LoginResponseDTO;
import com.matthew.employeemanagementsystem.dtos.user.UserResponseDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface UserModelMapper {
    UserResponseDTO mapUserEntityToUserResponseDTO(UserEntity userEntity);

    LoginResponseDTO mapUserEntityToLoginResponseDTO(UserEntity userEntity);
}
