package com.matthew.employeemanagementsystem.service.user;

import com.matthew.employeemanagementsystem.dtos.user.DeleteUserRequestDTO;
import com.matthew.employeemanagementsystem.dtos.user.RegisterNewUserRequestDTO;
import com.matthew.employeemanagementsystem.dtos.user.UserResponseDTO;

import javax.transaction.Transactional;

public interface UserManagementService {
    @Transactional
    UserResponseDTO registerNewUser(RegisterNewUserRequestDTO requestDTO);

    @Transactional
    void deleteUser(DeleteUserRequestDTO deleteUserRequestDTO);

}
