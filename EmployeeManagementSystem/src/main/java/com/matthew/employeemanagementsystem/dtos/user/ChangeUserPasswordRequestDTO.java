package com.matthew.employeemanagementsystem.dtos.user;

public record ChangeUserPasswordRequestDTO(String oldPassword, String repeatOldPassword,
                                           String newPassword,
                                           String repeatNewPassword) {
}
