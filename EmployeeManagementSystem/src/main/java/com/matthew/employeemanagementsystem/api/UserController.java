package com.matthew.employeemanagementsystem.api;

import com.matthew.employeemanagementsystem.dtos.user.LoginRequestDTO;
import com.matthew.employeemanagementsystem.dtos.user.LoginResponseDTO;
import com.matthew.employeemanagementsystem.dtos.user.RegisterNewUserRequestDTO;
import com.matthew.employeemanagementsystem.dtos.user.UserResponseDTO;
import com.matthew.employeemanagementsystem.service.user.UserManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/")
class UserController {
    private final UserManagementService userManagementService;

    @PostMapping("register")
    public ResponseEntity<UserResponseDTO> registerNewUser(@RequestBody RegisterNewUserRequestDTO requestDTO) {
        return new ResponseEntity<>(userManagementService.registerNewUser(requestDTO), HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO requestDTO) {
        return new ResponseEntity<>(userManagementService.login(requestDTO), HttpStatus.OK);
    }
}
