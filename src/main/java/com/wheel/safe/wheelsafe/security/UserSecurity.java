package com.wheel.safe.wheelsafe.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.wheel.safe.wheelsafe.user.entity.User;
import com.wheel.safe.wheelsafe.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component("userSecurity")
@RequiredArgsConstructor
public class UserSecurity {
    private final UserRepository userRepository;

    public boolean isCurrentUser(Authentication authentication, Long userId){
        String email = authentication.getName();
        User user = userRepository.findByEmail(email).orElse(null);

        return user != null && user.getId().equals(userId);
    }

}
