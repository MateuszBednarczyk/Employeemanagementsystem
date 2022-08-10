package com.matthew.employeemanagementsystem.service.jwt;

import com.matthew.employeemanagementsystem.domain.entities.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public interface TokenService {
    String generateAccessToken(UserEntity user, String issuer);

    String generateRefreshToken(UserEntity user, String issuer);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
