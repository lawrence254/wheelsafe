package com.wheel.safe.wheelsafe.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
/**
 * @Author: Lawrence Karanja
 * @Date: 2023-10-01
 * @Description: Controller for authentication and authorization endpoints
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wheel.safe.wheelsafe.security.dto.AuthRequest;
import com.wheel.safe.wheelsafe.security.dto.AuthResponse;
import com.wheel.safe.wheelsafe.security.dto.RefreshTokenRequest;
import com.wheel.safe.wheelsafe.security.dto.RegisterRequest;
import com.wheel.safe.wheelsafe.security.service.AuthenticationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Authentication and token management endpoints")
public class AuthController {
    private final AuthenticationService authService;

    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Registers a new user account and returns authentication details")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid registration data")
    })
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    @Operation(summary = "Authenticate user", description = "Authenticates a user and returns an access token and refresh token")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Authentication successful"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials")
    })
    public ResponseEntity<AuthResponse> authenticate(@Valid @RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }

    @PostMapping("/logout")
    @Operation(summary = "Logout current user", description = "Logs out the currently authenticated user")
    @ApiResponse(responseCode = "200", description = "Logout successful")
    public ResponseEntity<Void> logout(Authentication authentication) {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/refresh-token")
    @Operation(summary = "Refresh authentication token", description = "Exchanges a refresh token for a new access token")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Token refreshed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid refresh token")
    })
    public ResponseEntity<AuthResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(authService.refreshToken(request));
    }

    @PostMapping("/mfa-enable")
    @Operation(summary = "Enable MFA for user", description = "Enables multi-factor authentication for the authenticated user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "MFA enabled successfully"),
            @ApiResponse(responseCode = "401", description = "Authentication required")
    })
    public ResponseEntity<Void> enableMfa(@RequestBody AuthRequest request) {
        authService.authenticate(request);
        authService.enableMfa(request.getEmail());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/mfa-disable")
    @Operation(summary = "Disable MFA for user", description = "Disables multi-factor authentication for the authenticated user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "MFA disabled successfully"),
            @ApiResponse(responseCode = "401", description = "Authentication required")
    })
    public ResponseEntity<Void> disableMfa(@RequestBody AuthRequest request) {
        authService.authenticate(request);
        authService.disableMfa(request.getEmail());
        return ResponseEntity.ok().build();
    }

}
