package com.wheel.safe.wheelsafe.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wheel.safe.wheelsafe.security.exception.InvalidCredentialsException;
import com.wheel.safe.wheelsafe.user.dto.UserProfileDTO;
import com.wheel.safe.wheelsafe.user.entity.User;
import com.wheel.safe.wheelsafe.user.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserProfileDTO getUserProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new InvalidCredentialsException("User not found"));

        return mapToUserProfileDTO(user);
    }

    public UserProfileDTO getUserProfileById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new InvalidCredentialsException("User not found"));

        return mapToUserProfileDTO(user);
    }

    @Transactional
    public UserProfileDTO updateUserProfile(String email, UserProfileDTO profileDTO) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new InvalidCredentialsException("User not found"));

        // Only update allowed fields
        user.setFirstName(profileDTO.getFirstName());
        user.setLastName(profileDTO.getLastName());
        user.setPhoneNumber(profileDTO.getPhoneNumber());

        userRepository.save(user);

        return mapToUserProfileDTO(user);
    }

    @Transactional
    public void updatePassword(String email, String currentPassword, String newPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new InvalidCredentialsException("User not found"));

        // Verify current password
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new InvalidCredentialsException("Current password is incorrect");
        }

        // Update password
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new InvalidCredentialsException("User not found");
        }

        userRepository.deleteById(id);
    }

    private UserProfileDTO mapToUserProfileDTO(User user) {
        return UserProfileDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getUsername())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .mfaEnabled(user.isMfaEnabled())
                .build();
    }
}