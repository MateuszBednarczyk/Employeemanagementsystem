package com.matthew.employeemanagementsystem.api;

import com.matthew.employeemanagementsystem.domain.entities.UserEntity;
import com.matthew.employeemanagementsystem.dtos.user.RegisterNewUserRequestDTO;
import com.matthew.employeemanagementsystem.service.user.UserManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.rmi.UnexpectedException;

@Controller
@RequiredArgsConstructor
class RegisterController {

    private final UserManagementService userManagementService;

    @GetMapping("/register")
    public String showRegistrarionForm() {
        return "register.html";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterNewUserRequestDTO requestDTO) throws UnexpectedException {
        userManagementService.registerNewUser(requestDTO);

        return "redirect:/login";
    }
}
