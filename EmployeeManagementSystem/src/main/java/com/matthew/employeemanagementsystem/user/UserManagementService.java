package com.matthew.employeemanagementsystem.user;

import com.matthew.employeemanagementsystem.user.dtos.RegisterNewUserRequestDTO;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.rmi.UnexpectedException;

interface UserManagementService {
    @Transactional
    ResponseEntity<String> registerNewUser(RegisterNewUserRequestDTO requestDTO) throws UnexpectedException;
}
