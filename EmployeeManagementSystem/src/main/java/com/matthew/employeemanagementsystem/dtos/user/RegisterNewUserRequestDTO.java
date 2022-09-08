package com.matthew.employeemanagementsystem.dtos.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record RegisterNewUserRequestDTO(@NotBlank @Size(min = 1, max = 25, message = "Username has to be between 1 and 25 characters") String username,
                                        @NotBlank String password,
                                        @NotBlank @Size(min = 1, max = 25, message = "Email has to be between 1 and 100 characters") String email,
                                        @NotBlank String department,
                                        @NotBlank String role) {
}
