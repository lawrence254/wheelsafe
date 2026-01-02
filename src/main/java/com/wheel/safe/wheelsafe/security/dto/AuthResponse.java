package com.wheel.safe.wheelsafe.security.dto;

import java.util.List;

import com.wheel.safe.wheelsafe.user.entity.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
    private String tokenType="Bearer";
    private long expiresIn;
    private boolean mfaRequired;
    private String userId;
    private List<UserRole> userRoles;
}
