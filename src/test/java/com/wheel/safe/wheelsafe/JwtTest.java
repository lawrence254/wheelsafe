package com.wheel.safe.wheelsafe;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtTest {
    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUkVHVUxBUl9VU0VSIiwidXNlcklkIjo3LCJzdWIiOiJlbWFpbC50ZXN0NEB0ZXN0LmNvbSIsImlhdCI6MTc0NjExMjY0NywiZXhwIjoxNzQ2MTE2MjQ3fQ.WjrtnSRXTinfv1CqtRfjpgteL62Yy7DDeKgM4GUOV_8";

        // Replace this with your actual JWT secret
        String jwtSecret = "KjYmKIsM7Y14Slg7XMfoS7fBG84vptOib9EPacqUBUkbN8FXnfs4RxVKRjNhZsYHBNB4RcbvQWXCmP45IDouV4JC5I3YNLcvmC5M1sWfTI1t1D0hsUlrxV63QXM2bu40";

        SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));

        try {
            Claims claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            System.out.println("Token parsed successfully!");
            System.out.println("Subject: " + claims.getSubject());
            System.out.println("Role: " + claims.get("role"));
            System.out.println("User ID: " + claims.get("userId"));
        } catch (Exception e) {
            System.out.println("Token parsing failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}