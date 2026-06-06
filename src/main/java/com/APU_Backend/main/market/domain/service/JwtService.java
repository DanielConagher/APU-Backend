package com.APU_Backend.main.market.domain.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    private final String SECRET = "MiClaveSuperSecretaParaJWT2026APUProyecto";

    public String generateToken(Integer userId, String correo, String rol) {

        SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());

        return Jwts.builder()
                .subject(correo)
                .claim("id", userId)
                .claim("rol", rol)
                .issuedAt(new Date())
                .expiration(
                        new Date(System.currentTimeMillis()
                                + 86400000))
                .signWith(key)
                .compact();
    }

    public Integer extractId(String token) {

        SecretKey key = Keys.hmacShaKeyFor(
                SECRET.getBytes());

        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return claims.get(
                "id",
                Integer.class);
    }
}