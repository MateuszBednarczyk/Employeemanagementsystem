package com.matthew.employeemanagementsystem.dtos.user;

import javax.validation.constraints.NotBlank;

public record ChangeUserPasswordRequestDTO(@NotBlank(message = "New password cannot be null") String oldPassword,
                                           @NotBlank(message = "Repeating password cannot be null") String repeatOldPassword,
                                           @NotBlank(message = "New password cannot be null") String newPassword,
                                           @NotBlank(message = "Repeating password cannot be null") String repeatNewPassword) {
}
