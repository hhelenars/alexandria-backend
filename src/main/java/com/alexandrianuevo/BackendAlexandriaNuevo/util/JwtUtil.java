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

    public Long extraerIdUsuario(String token) {
        Claims claims = getClaims(token);
        return Long.parseLong(claims.getSubject());
    }

    public boolean validarToken(String token, Long usuarioId) {
        try {
            Long idEnToken = extraerIdUsuario(token);
            return idEnToken.equals(usuarioId) && !estaExpirado(token);
        } catch (Exception e) {
            return false;
        }
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean estaExpirado(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }
}