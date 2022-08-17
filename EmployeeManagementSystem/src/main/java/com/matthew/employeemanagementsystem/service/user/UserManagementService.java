package com.matthew.employeemanagementsystem.service.user;

import com.matthew.employeemanagementsystem.dtos.user.*;

public interface UserManagementService {

    UserResponseDTO registerNewUser(RegisterNewUserRequestDTO requestDTO);

    void deleteUser(DeleteUserRequestDTO deleteUserRequestDTO);

    LoginResponseDTO login(LoginRequestDTO requestDTO);

}
