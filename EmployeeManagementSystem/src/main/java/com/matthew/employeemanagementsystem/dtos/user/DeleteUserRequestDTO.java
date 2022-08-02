package com.matthew.employeemanagementsystem.dtos.user;

import java.security.Principal;

public record DeleteUserRequestDTO(Principal principal, String username) {
}
