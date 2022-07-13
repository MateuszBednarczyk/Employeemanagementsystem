package com.matthew.employeemanagementsystem.service.user;

import com.matthew.employeemanagementsystem.dtos.user.RegisterNewUserRequestDTO;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.rmi.UnexpectedException;

public interface UserManagementService {
    @Transactional
    ResponseEntity<String> registerNewUser(RegisterNewUserRequestDTO requestDTO) throws UnexpectedException;
}
