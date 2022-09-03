package com.matthew.employeemanagementsystem.api;

import com.matthew.employeemanagementsystem.dtos.user.*;
import com.matthew.employeemanagementsystem.service.user.UserFindingService;
import com.matthew.employeemanagementsystem.service.user.UserManagementService;
import com.matthew.employeemanagementsystem.service.verificationtoken.VerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users/")
class UserController {
    private final UserManagementService userManagementService;
    private final UserFindingService userFindingService;
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
    public ModelAndView verifyUser(HttpServletRequest request, @RequestParam String tokenValue) {
        verificationService.verify(tokenValue);

        return new ModelAndView("redirect:http://" + request.getServerName() + ":" + request.getServerPort());
    }

    @GetMapping("/superadmins")
    public ResponseEntity<List<UserResponseDTO>> findUsersByRoleTypeSuperAdmin() {
        return new ResponseEntity<>(userFindingService.findUsersByRoleTypeSuperAdmin(), HttpStatus.OK);
    }

    @GetMapping("/admins")
    public ResponseEntity<List<UserResponseDTO>> findUsersByRoleTypeAdmin() {
        return new ResponseEntity<>(userFindingService.findUsersByRoleTypeAdmin(), HttpStatus.OK);
    }

    @GetMapping("/moderators")
    public ResponseEntity<List<UserResponseDTO>> findUsersByRoleTypeModerator() {
        return new ResponseEntity<>(userFindingService.findUsersByRoleTypeModerator(), HttpStatus.OK);
    }

}
