package com.matthew.employeemanagementsystem.service.user;

import com.matthew.employeemanagementsystem.dtos.user.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

public interface UserManagementService {

    UserResponseDTO registerNewUser(HttpServletRequest request, Principal loggedUser, RegisterNewUserRequestDTO requestDTO);

    void deleteUser(Principal loggedUser, DeleteUserRequestDTO requestDTO);

    LoginResponseDTO login(LoginRequestDTO requestDTO);

    void setupSuperAdminUser(RegisterNewUserRequestDTO requestDTO);

    void changeUserPassword(Principal loggedUser, ChangeUserPasswordRequestDTO requestDTO);

}
