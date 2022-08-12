package com.matthew.employeemanagementsystem.service.user;

import com.matthew.employeemanagementsystem.dtos.user.*;

import javax.transaction.Transactional;

public interface UserManagementService {
    @Transactional
    UserResponseDTO registerNewUser(RegisterNewUserRequestDTO requestDTO);

    @Transactional
    void deleteUser(DeleteUserRequestDTO deleteUserRequestDTO);

    LoginResponseDTO login(LoginRequestDTO requestDTO);

}
