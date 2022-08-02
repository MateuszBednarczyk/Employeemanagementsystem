package com.matthew.employeemanagementsystem.service.user;

import com.matthew.employeemanagementsystem.dtos.user.DeleteUserRequestDTO;
import com.matthew.employeemanagementsystem.dtos.user.RegisterNewUserRequestDTO;
import com.matthew.employeemanagementsystem.dtos.user.UserResponseDTO;

import javax.transaction.Transactional;
import java.nio.file.AccessDeniedException;
import java.rmi.UnexpectedException;

public interface UserManagementService {
    @Transactional
    UserResponseDTO registerNewUser(RegisterNewUserRequestDTO requestDTO) throws UnexpectedException;

    @Transactional
    void deleteUser(DeleteUserRequestDTO deleteUserRequestDTO) throws AccessDeniedException;

}
