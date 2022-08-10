package com.matthew.employeemanagementsystem.service.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Service
public interface AuthenticationService {
    Map<Object, Object> successfulAuthentication(HttpServletRequest request, HttpServletResponse response, Authentication authResult);

    UsernamePasswordAuthenticationToken createUsernameAuthenticationToken(HttpServletRequest request, HttpServletResponse response);
}
