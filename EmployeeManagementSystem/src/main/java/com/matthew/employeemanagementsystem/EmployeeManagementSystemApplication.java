package com.matthew.employeemanagementsystem;

import com.matthew.employeemanagementsystem.dtos.department.AddNewDepartmentRequestDTO;
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
    @Value("${setup.superadmin.username}")
    private String setupUsername;

    @Value("${setup.superadmin.password}")
    private String setupPassword;

    @Value("${setup.superadmin.department.name}")
    private String setupDepartmentName;

    @Value("${setup.superadmin.mail}")
    private String setupSuperAdminMail;

    @Value("${front.end.application.uri}")
    private String frontEndOrigin;

    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagementSystemApplication.class, args);
    }

    @Bean
    @EventListener(EmployeeManagementSystemApplication.class)
    public void administratorAccountSetup() {
        departmentFacade.addNewDepartment(new AddNewDepartmentRequestDTO(setupDepartmentName));
        userManagementService.setupSuperAdminUser(new RegisterNewUserRequestDTO(setupUsername, setupPassword, setupSuperAdminMail, setupDepartmentName, "ROLE_SUPERADMIN"));
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**").allowedOrigins(frontEndOrigin).allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH");
            }
        };
    }
}
