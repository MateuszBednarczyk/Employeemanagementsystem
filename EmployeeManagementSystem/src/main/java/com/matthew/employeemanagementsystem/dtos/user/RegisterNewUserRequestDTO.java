package com.matthew.employeemanagementsystem.dtos.user;

import javax.validation.constraints.NotBlank;

public record RegisterNewUserRequestDTO(@NotBlank(message = "Username cannot be null") String username,
                                        @NotBlank(message = "Password cannot be null") String password,
                                        @NotBlank(message = "E-Mail cannot be null") String email,
                                        @NotBlank(message = "Department name cannot be null") String department,
                                        @NotBlank(message = "Role cannot be null") String role) {
}
