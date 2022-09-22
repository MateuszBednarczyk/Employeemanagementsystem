package com.matthew.employeemanagementsystem.dtos.user;

import javax.validation.constraints.NotBlank;

public record LoginRequestDTO(@NotBlank(message = "Username cannot be null") String username,
                              @NotBlank(message = "Password cannot be null") String password) {
}
