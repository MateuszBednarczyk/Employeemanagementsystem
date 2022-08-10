package com.matthew.employeemanagementsystem.service.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.matthew.employeemanagementsystem.domain.entities.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

import static com.matthew.employeemanagementsystem.finals.JwtFinals.*;

@Service
public class TokenServiceImpl implements TokenService {

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
                .withIssuer(issuer)
                .sign(algorithm);
    }
}
