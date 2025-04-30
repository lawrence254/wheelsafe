package com.wheel.safe.wheelsafe.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wheel.safe.wheelsafe.security.dto.AuthRequest;
import com.wheel.safe.wheelsafe.security.dto.AuthResponse;
import com.wheel.safe.wheelsafe.security.dto.RefreshTokenRequest;
import com.wheel.safe.wheelsafe.security.dto.RegisterRequest;
import com.wheel.safe.wheelsafe.security.service.AuthenticationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request){
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest request){
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(Authentication authentication){
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/refresh-token")
    public ResponseEntity<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request){
        return ResponseEntity.ok(authService.refreshToken(request));
    }
    
    @PostMapping("/mfa-enable")
    public ResponseEntity<Void> enableMfa(@RequestBody AuthRequest request){
        authService.authenticate(request);
        authService.enableMfa(request.getEmail());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/mfa-disable")
    public ResponseEntity<Void> disableMfa(@RequestBody AuthRequest request){
        authService.authenticate(request);
        authService.disableMfa(request.getEmail());
        return ResponseEntity.ok().build();
    }
        
}
