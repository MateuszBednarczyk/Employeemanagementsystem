package com.matthew.employeemanagementsystem.dtos.user;

import javax.validation.constraints.NotBlank;

public record ChangeUserPasswordRequestDTO(@NotBlank String oldPassword,
                                           @NotBlank String repeatOldPassword,
                                           @NotBlank String newPassword,
                                           @NotBlank String repeatNewPassword) {
}
