package com.wheel.safe.wheelsafe.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wheel.safe.wheelsafe.user.dto.UserProfileDTO;
import com.wheel.safe.wheelsafe.user.dto.RoleUpdateDTO;
import com.wheel.safe.wheelsafe.user.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<UserProfileDTO> getCurrentUserProfile(Authentication authentication){
        return ResponseEntity.ok(userService.getUserProfile(authentication.getName()));
    }

    @PutMapping("/profile")
    public ResponseEntity<UserProfileDTO> updateCurrentUserProfile(Authentication authentication, @RequestBody UserProfileDTO profile){
        return ResponseEntity.ok(userService.updateUserProfile(authentication.getName(), profile));
    }

    @PutMapping("/update-roles")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserProfileDTO> updateUserRoles(@RequestBody RoleUpdateDTO roleUpdates) {
        return ResponseEntity.ok(userService.updateUserRoles(roleUpdates));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @userSecurity.isCurrentUser(authentication, #id)")
    public ResponseEntity<UserProfileDTO> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserProfileById(id));
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}