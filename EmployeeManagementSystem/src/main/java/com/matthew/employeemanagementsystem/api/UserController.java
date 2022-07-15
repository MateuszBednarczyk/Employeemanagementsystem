package com.matthew.employeemanagementsystem.api;

import com.matthew.employeemanagementsystem.dtos.user.RegisterNewUserRequestDTO;
import com.matthew.employeemanagementsystem.service.user.UserManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.UnexpectedException;

@RestController
@RequiredArgsConstructor
class UserController {
    private final UserManagementService userManagementService;

    @PostMapping("/api/users/register")
    public ResponseEntity<String> registerNewUser(@RequestBody RegisterNewUserRequestDTO requestDTO) throws UnexpectedException {

        return userManagementService.registerNewUser(requestDTO);
    }
}
