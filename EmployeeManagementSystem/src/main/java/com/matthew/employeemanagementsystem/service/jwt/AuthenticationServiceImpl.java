package com.matthew.employeemanagementsystem.service.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matthew.employeemanagementsystem.domain.entities.UserEntity;
import com.matthew.employeemanagementsystem.dtos.user.LoginResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final TokenService tokenService;

    @Override
    public UsernamePasswordAuthenticationToken createUsernameAuthenticationToken(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, String> requestMap = new ObjectMapper().readValue(request.getInputStream(), Map.class);
            String username = requestMap.get("username");
            String password = requestMap.get("password");
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);

            return usernamePasswordAuthenticationToken;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<Object, Object> successfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult) {
        ModelMapper modelMapper = new ModelMapper();
        UserEntity user = (UserEntity) authResult.getPrincipal();
        String issuer = request.getRequestURI();
        String accessToken = tokenService.generateAccessToken(user, issuer);
        String refreshToken = tokenService.generateRefreshToken(user, issuer);
        Map<Object, Object> responseBody = new HashMap<>();
        responseBody.put("access_token", accessToken);
        responseBody.put("refresh_token", refreshToken);
        responseBody.put("user", modelMapper.map(user, LoginResponseDTO.class));

        return responseBody;
    }


}
