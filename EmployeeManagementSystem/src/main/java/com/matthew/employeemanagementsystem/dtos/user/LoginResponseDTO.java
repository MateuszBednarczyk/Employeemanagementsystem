package com.matthew.employeemanagementsystem.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Immutable
public class LoginResponseDTO {
    private String username;
}
