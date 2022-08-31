package com.matthew.employeemanagementsystem.service.user;

import com.matthew.employeemanagementsystem.dtos.user.*;

import java.security.Principal;

public interface UserManagementService {

    UserResponseDTO registerNewUser(Principal loggedUser, RegisterNewUserRequestDTO requestDTO);

    void deleteUser(Principal principal, DeleteUserRequestDTO requestDTO);

    LoginResponseDTO login(LoginRequestDTO requestDTO);

    void setupSuperAdminUser(RegisterNewUserRequestDTO requestDTO);

    void changeUserPassword(Principal loggedUser, ChangeUserPasswordRequestDTO requestDTO);

}
