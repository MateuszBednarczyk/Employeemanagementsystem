package com.matthew.employeemanagementsystem.api;

import com.matthew.employeemanagementsystem.dtos.user.*;
import com.matthew.employeemanagementsystem.service.user.UserManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/")
class UserController {
    private final UserManagementService userManagementService;

    @PostMapping("register")
    public ResponseEntity<UserResponseDTO> registerNewUser(Principal loggedUser, @RequestBody RegisterNewUserRequestDTO requestDTO) {
        return new ResponseEntity<>(userManagementService.registerNewUser(loggedUser, requestDTO), HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO requestDTO) {
        return new ResponseEntity<>(userManagementService.login(requestDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(Principal principal, DeleteUserRequestDTO requestDTO) {
        userManagementService.deleteUser(principal, requestDTO);

        return new ResponseEntity<>(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK);
    }
}
