package com.matthew.employeemanagementsystem.service.jwt;

import com.matthew.employeemanagementsystem.domain.entities.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface TokenService {
    String generateAccessToken(UserEntity user, String issuer);

    String generateRefreshToken(UserEntity user, String issuer);
}
