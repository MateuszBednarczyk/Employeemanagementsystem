package com.matthew.employeemanagementsystem.api;

import com.matthew.employeemanagementsystem.dtos.user.*;
import com.matthew.employeemanagementsystem.service.user.UserManagementService;
import com.matthew.employeemanagementsystem.service.verificationtoken.VerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/")
class UserController {
    private final UserManagementService userManagementService;
    private final VerificationService verificationService;

    @PostMapping("register")
    public ResponseEntity<UserResponseDTO> registerNewUser(HttpServletRequest request, Principal loggedUser, @RequestBody RegisterNewUserRequestDTO requestDTO) {
        return new ResponseEntity<>(userManagementService.registerNewUser(request, loggedUser, requestDTO), HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO requestDTO) {
        return new ResponseEntity<>(userManagementService.login(requestDTO), HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(Principal principal, @RequestBody DeleteUserRequestDTO requestDTO) {
        userManagementService.deleteUser(principal, requestDTO);

        return new ResponseEntity<>(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK);
    }

    @PatchMapping("/change-password")
    public ResponseEntity<String> changeUserPassword(Principal loggedUser, @RequestBody ChangeUserPasswordRequestDTO requestDTO) {
        userManagementService.changeUserPassword(loggedUser, requestDTO);

        return new ResponseEntity<>(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK);
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyUser(@RequestParam String tokenValue) {
        verificationService.verify(tokenValue);

        return new ResponseEntity<>(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK);
    }

}
