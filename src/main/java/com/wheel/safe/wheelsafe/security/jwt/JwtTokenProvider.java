package com.wheel.safe.wheelsafe.security.jwt;


import com.wheel.safe.wheelsafe.JwtProperties;
import com.wheel.safe.wheelsafe.user.entity.User;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final JwtProperties jwtProperties;
    private SecretKey signingKey;

    @PostConstruct
    public void init() {
        this.signingKey = getSigningKey();
    }

    public String generateAccessToken(UserDetails userDetails) {
        User user = (User) userDetails;
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("roles", user.getUserRoles().stream().map(Enum::name).toList());

        return Jwts.builder()
                .claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtProperties.getRefresh()))
                .signWith(signingKey)
                .compact();
    }

    public String generateRefreshToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtProperties.getRefresh()))
                .signWith(signingKey)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(signingKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public long getAccessTokenExpirationTime() {
        return jwtProperties.getAccess();
    }
    
    private SecretKey getSigningKey() {
        if (jwtProperties.isSecretIsBase64()) {
            // Base64 decode the secret before creating the key
            byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.getSecret());
            return Keys.hmacShaKeyFor(keyBytes);
        } else {
            // Use the secret directly as UTF-8 bytes
            return Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8));
        }
    }

    // Helper method for jwt.io testing - gets the raw secret string for jwt.io
    public String getSecretForJwtIo() {
        if (jwtProperties.isSecretIsBase64()) {
            // For jwt.io, you'd either:
            // 1. Use the original Base64 string WITH the "secret base64 encoded" option
            // checked
            return jwtProperties.getSecret() + " (use with 'secret base64 encoded' checkbox)";

            // OR
            // 2. Return the decoded string without checking the checkbox
            // return new String(Decoders.BASE64.decode(jwtProperties.getSecret()),
            // StandardCharsets.UTF_8);
        } else {
            // If not base64 encoded, just return the raw secret
            return jwtProperties.getSecret();
        }
    }
}