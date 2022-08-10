package com.matthew.employeemanagementsystem.filter;

import com.matthew.employeemanagementsystem.service.jwt.AuthorizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {

    private final AuthorizationService authorizationService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals("/api/users/login")) {
            filterChain.doFilter(request, response);
        } else {
            if (request.getHeader(AUTHORIZATION) != null) {
                authorizationService.tryAuthorize(request, response, filterChain);
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }
}
