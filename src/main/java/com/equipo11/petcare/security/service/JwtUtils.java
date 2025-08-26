package com.equipo11.petcare.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;


@Component
public class JwtUtils {

    @Value("${petcare.app.jwtSecret}")
    private String jwtSecret;

    @Value("${petcare.app.jwtExpirationMs}")
    private long jwtExpirationMs;

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(jwtSecret);
    }

    public String generateToken(String username, String role) {
        Instant expiry = LocalDateTime.now().plusHours(jwtExpirationMs).toInstant(ZoneOffset.of("-04:00"));

        return JWT.create()
                .withIssuer("petcare")
                .withSubject(username)
                .withClaim("role", role)
                .withExpiresAt(expiry)
                .sign(getAlgorithm());
    }

    public String getUsernameFromToken(String token) {
        DecodedJWT jwt = JWT.require(getAlgorithm()).build().verify(token);
        return jwt.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(getAlgorithm()).build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            // Aquí se puede loguear e.getMessage()
            return false;
        }
    }
}
