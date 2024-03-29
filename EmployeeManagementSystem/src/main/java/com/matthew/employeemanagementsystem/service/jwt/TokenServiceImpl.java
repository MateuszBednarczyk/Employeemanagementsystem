package com.matthew.employeemanagementsystem.service.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.matthew.employeemanagementsystem.domain.entities.UserEntity;
import com.matthew.employeemanagementsystem.service.user.UserFindingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static com.matthew.employeemanagementsystem.finals.JwtFinals.*;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final UserFindingService userFindingService;

    Algorithm algorithm = Algorithm.HMAC256(SECRET.getBytes());

    @Override
    public String generateAccessToken(UserEntity user, String issuer) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_LIFETIME * 10))
                .withClaim(CLAIMS, user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    @Override
    public String generateRefreshToken(UserEntity user, String issuer) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_TOKEN_LIFETIME * 12))
                .withClaim(CLAIMS, user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String oldRefreshToken = request.getHeader(AUTHORIZATION).substring(TOKEN_PREFIX.length());
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(oldRefreshToken);
            String username = decodedJWT.getSubject();
            UserEntity user = userFindingService.getUserEntity(username);
            String accessToken = generateAccessToken(user, request.getRequestURI());
            String newRefreshToken = generateRefreshToken(user, request.getRequestURI());
            Map<String, String> tokens = new HashMap<>();
            tokens.put("access_token", accessToken);
            tokens.put("refresh_token", newRefreshToken);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getOutputStream(), tokens);
        } catch (Exception e) {
            throw e;
        }
    }
}
