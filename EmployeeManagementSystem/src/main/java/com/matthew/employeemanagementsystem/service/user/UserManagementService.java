package com.matthew.employeemanagementsystem.service.user;

import com.matthew.employeemanagementsystem.dtos.user.RegisterNewUserRequestDTO;
import com.matthew.employeemanagementsystem.dtos.user.UserResponseDTO;

import javax.transaction.Transactional;
import java.rmi.UnexpectedException;

public interface UserManagementService {
    @Transactional
    UserResponseDTO registerNewUser(RegisterNewUserRequestDTO requestDTO) throws UnexpectedException;

}
