package com.pagil.teruel_express.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
@Component
public class JwtUtils {

    public static final String JWT_BEARER = "Bearer ";
    public static final String JWT_AUTHORIZATION = "Authorization";
    @Value("${JWT.SECRET-KEY}")
    public String SECRET_KEY;
    @Value("${JWT.EXPIRATION.DEV}")
    private static Long EXPIRATION_TIME_DEV;
    @Value("${JWT.EXPIRATION.ADMIN}")
    private static Long EXPIRATION_TIME_ADMIN;
    @Value("${JWT.EXPIRATION.CLIENT}")
    private static Long EXPIRATION_TIME_CLIENT;

    private static SecretKey key;

    @PostConstruct
    public void init() {
        key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    private static Date toExpireDate(Date start, String role){
        LocalDateTime dateTime = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime end;
        if(role.matches("DEV")) {
            end = dateTime.plusDays(EXPIRATION_TIME_DEV);
        } else if(role.matches("ADMIN")) {
            end = dateTime.plusHours(EXPIRATION_TIME_ADMIN);
        } else {
            end = dateTime.plusHours(EXPIRATION_TIME_CLIENT);
        }
        return Date.from(end.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static JwtToken createToken(String username, String role) {
        Date issuedAt = new Date();
        Date limit = toExpireDate(issuedAt, role);
        String token = Jwts.builder()
                .header().add("typ", "JWT")
                .and()
                .subject(username)
                .issuedAt(issuedAt)
                .expiration(limit)
                .signWith(key)
                .claim("role", role)
                .compact();
        return new JwtToken(token);
    }

    private static Claims getClaimsFromToken(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(refactorToken(token)).getPayload();
        } catch (JwtException ex) {
            log.error(String.format("Token invalido %s", ex.getMessage()));
        }
        return null;
    }

    public static String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    public static boolean isTokenValid(String token) {
        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(refactorToken(token));
            return true;
        } catch (JwtException ex) {
            log.error(String.format("Token invalido %s", ex.getMessage()));
        }
        return false;
    }

    private static String refactorToken(String token) {
        if (token.contains(JWT_BEARER)) {
            return token.substring(JWT_BEARER.length());
        }
        return token;
    }
}
