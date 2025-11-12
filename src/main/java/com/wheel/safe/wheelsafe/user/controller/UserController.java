package com.wheel.safe.wheelsafe.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wheel.safe.wheelsafe.user.dto.RoleUpdateDTO;
import com.wheel.safe.wheelsafe.user.dto.UserProfileDTO;
import com.wheel.safe.wheelsafe.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "Operations related to user profiles and administration")
public class UserController {
    private final UserService userService;

    @GetMapping("/profile")
    @Operation(summary = "Get current user profile", description = "Returns the profile of the currently authenticated user")
    @ApiResponse(responseCode = "200", description = "User profile retrieved successfully")
    public ResponseEntity<UserProfileDTO> getCurrentUserProfile(Authentication authentication) {
        return ResponseEntity.ok(userService.getUserProfile(authentication.getName()));
    }

    @PutMapping("/profile")
    @Operation(summary = "Update current user profile", description = "Updates the profile of the currently authenticated user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User profile updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<UserProfileDTO> updateCurrentUserProfile(Authentication authentication,
            @RequestBody UserProfileDTO profile) {
        return ResponseEntity.ok(userService.updateUserProfile(authentication.getName(), profile));
    }

    @PutMapping("/update-roles")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update user roles", description = "Updates roles for users (admin only)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User roles updated successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<UserProfileDTO> updateUserRoles(@RequestBody RoleUpdateDTO roleUpdates) {
        return ResponseEntity.ok(userService.updateUserRoles(roleUpdates));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @userSecurity.isCurrentUser(authentication, #id)")
    @Operation(summary = "Get user by ID", description = "Retrieves a user profile by ID (admin or own user)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User profile retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<UserProfileDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserProfileById(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete user", description = "Deletes a user by ID (admin only)")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}