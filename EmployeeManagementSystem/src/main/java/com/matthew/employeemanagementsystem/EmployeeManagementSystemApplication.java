package com.matthew.employeemanagementsystem;

import com.matthew.employeemanagementsystem.dtos.user.RegisterNewUserRequestDTO;
import com.matthew.employeemanagementsystem.service.department.DepartmentFacade;
import com.matthew.employeemanagementsystem.service.user.UserManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@RequiredArgsConstructor
@EnableGlobalAuthentication
public class EmployeeManagementSystemApplication {

    private final UserManagementService userManagementService;
    private final DepartmentFacade departmentFacade;

    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    @Value("${admin.department.name}")
    private String adminDepartmentName;

    @Value("${front.end.application.uri}")
    private String frontEndOrigin;

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementSystemApplication.class, args);
    }

    @Bean
    @EventListener(EmployeeManagementSystemApplication.class)
    public void administratorAccountSetup() {
        userManagementService.registerNewUser(new RegisterNewUserRequestDTO(adminUsername, adminPassword, adminDepartmentName, "ROLE_ADMIN"));
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**").allowedOrigins(frontEndOrigin);
            }
        };
    }
}
