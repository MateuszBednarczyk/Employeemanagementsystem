package com.matthew.employeemanagementsystem.service.user;

import com.matthew.employeemanagementsystem.dtos.user.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

public interface UserManagementService {
    @Transactional
    UserResponseDTO registerNewUser(RegisterNewUserRequestDTO requestDTO);

    @Transactional
    void deleteUser(DeleteUserRequestDTO deleteUserRequestDTO);

    LoginResponseDTO login(LoginRequestDTO requestDTO);

    void refreshToken(HttpServletRequest request, HttpServletResponse response);

}
