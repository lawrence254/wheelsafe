package com.wheel.safe.wheelsafe.security.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wheel.safe.wheelsafe.security.dto.AuthRequest;
import com.wheel.safe.wheelsafe.security.dto.AuthResponse;
import com.wheel.safe.wheelsafe.security.dto.RefreshTokenRequest;
import com.wheel.safe.wheelsafe.security.dto.RegisterRequest;
import com.wheel.safe.wheelsafe.security.exception.InvalidCredentialsException;
import com.wheel.safe.wheelsafe.security.exception.TokenRefreshException;
import com.wheel.safe.wheelsafe.security.exception.UserAlreadyExistsException;
import com.wheel.safe.wheelsafe.security.jwt.JwtTokenProvider;
import com.wheel.safe.wheelsafe.security.mfa.MfaService;
import com.wheel.safe.wheelsafe.user.entity.User;
import com.wheel.safe.wheelsafe.user.entity.UserRole;
import com.wheel.safe.wheelsafe.user.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final MfaService mfaService;

    @Transactional
    public AuthResponse register(RegisterRequest request){
        if(userRepository.existsByEmail(request.getEmail())){
            throw new UserAlreadyExistsException("Email already in use");
        }

        User user = User.builder()
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .email(request.getEmail())
            .password(request.getPassword())
            .phoneNumber(request.getPhoneNumber())
            .role(request.getRole())
            .enabled(true)
            .accountNonLocked(true)
            .mfaEnabled(false)
            .build();
            
        if(request.getRole() != UserRole.REGULAR_USER){
            user.setEnabled(false);
        }

        user = userRepository.save(user);

        String accessToken = jwtTokenProvider.generateAccessToken(user);
        String refreshToken = jwtTokenProvider.generateRefreshToken(user);

        return AuthResponse.builder()
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .tokenType("Bearer")
            .expiresIn(jwtTokenProvider.getAccessTokenExpirationTime()/1000)
            .userId(user.getId().toString())
            .userRole(user.getRole().toString())
            .mfaRequired(false)
            .build();
    }

    public AuthResponse authenticate(AuthRequest request){
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
            User user=(User)authentication.getPrincipal();

            if (user.isMfaEnabled()) {
                if (request.getMfaCode() == null || request.getMfaCode().isEmpty()) {
                    return AuthResponse.builder()
                    .mfaRequired(true)
                    .build();
                }
                if (!mfaService.verifyCode(user.getMfaSecret(), request.getMfaCode())) {
                    throw new InvalidCredentialsException("Invalid MFA Code");
                }
            }

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String accessToken = jwtTokenProvider.generateAccessToken(user);
            String refreshToken = jwtTokenProvider.generateRefreshToken(user);

            return AuthResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .tokenType("Bearer")
                    .expiresIn(jwtTokenProvider.getAccessTokenExpirationTime() / 1000)
                    .userId(user.getId().toString())
                    .userRole(user.getRole().toString())
                    .mfaRequired(false)
                    .build();

        } catch (BadCredentialsException e) {
            throw new InvalidCredentialsException("Invalid email or password");
        }
    }

    public AuthResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        try {
            String refreshToken = refreshTokenRequest.getRefreshToken();
            String email = jwtTokenProvider.extractUsername(refreshToken);
            
            UserDetails userDetails = userRepository.findByEmail(email)
                    .orElseThrow(() -> new TokenRefreshException("Invalid refresh token"));
            
            if (!jwtTokenProvider.validateToken(refreshToken, userDetails)) {
                throw new TokenRefreshException("Invalid refresh token");
            }
            
            User user = (User) userDetails;
            
            // Generate new access token
            String accessToken = jwtTokenProvider.generateAccessToken(user);
            
            return AuthResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken) // Reuse the same refresh token
                    .tokenType("Bearer")
                    .expiresIn(jwtTokenProvider.getAccessTokenExpirationTime() / 1000)
                    .userId(user.getId().toString())
                    .userRole(user.getRole().toString())
                    .mfaRequired(false)
                    .build();
            
        } catch (Exception e) {
            throw new TokenRefreshException("Failed to refresh token: " + e.getMessage());
        }
    }

    @Transactional
    public void enableMfa(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new InvalidCredentialsException("User not found"));
        
        if (!user.isMfaEnabled()) {
            String mfaSecret = mfaService.generateSecret();
            user.setMfaSecret(mfaSecret);
            user.setMfaEnabled(true);
            userRepository.save(user);
        }
    }
    
    @Transactional
    public void disableMfa(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new InvalidCredentialsException("User not found"));
        
        if (user.isMfaEnabled()) {
            user.setMfaSecret(null);
            user.setMfaEnabled(false);
            userRepository.save(user);
        }
    }

}
