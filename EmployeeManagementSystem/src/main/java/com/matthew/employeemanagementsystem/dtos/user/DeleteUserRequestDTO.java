package com.matthew.employeemanagementsystem.dtos.user;

import javax.validation.constraints.NotBlank;

public record DeleteUserRequestDTO(@NotBlank String username) {
}
