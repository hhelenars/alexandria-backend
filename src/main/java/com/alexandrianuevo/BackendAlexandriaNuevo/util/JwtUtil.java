package com.alexandrianuevo.BackendAlexandriaNuevo.util;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    private final String SECRET_KEY = "aLEXandria2005"; // Debe coincidir con el frontend
    private final long EXPIRATION_TIME = 86400000; // 1 día

    public String generarToken(Long id, String primerNombre, String segundoNombre) {
        return Jwts.builder()
                .setSubject(String.valueOf(id))
                .claim("primerNombre", primerNombre)
                .claim("segundoNombre", segundoNombre)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
}