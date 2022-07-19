package com.matthew.employeemanagementsystem.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class LoginController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "login.html";
    }

    @GetMapping("/home")
    public String successLogin() {
        return "home.html";
    }
}
