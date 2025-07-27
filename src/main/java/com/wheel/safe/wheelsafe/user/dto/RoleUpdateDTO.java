package com.wheel.safe.wheelsafe.user.dto;

import java.util.List;
import java.util.stream.Collectors;
import com.wheel.safe.wheelsafe.user.entity.UserRole;
import com.wheel.safe.wheelsafe.user.dto.UserProfileDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleUpdateDTO {
    @NotNull(message = "User ID is required")
    private Long userId;
    @NotBlank(message = "Roles are required")
    @Size(min = 1, message = "At least one role must be specified")
    private List<String> roles;

    public static RoleUpdateDTO fromUserProfileEntity(UserProfileDTO userProfile) {
        return RoleUpdateDTO.builder()
                .userId(userProfile.getId())
                .roles(userProfile.getRoles().stream().map(role -> role.name()).collect(Collectors.toList()))
                .build();
    }

    public static UserProfileDTO toUserProfileEntity(RoleUpdateDTO roleUpdateDTO) {
        UserProfileDTO userProfile = new UserProfileDTO();
        userProfile.setId(roleUpdateDTO.getUserId());
        userProfile.setRoles(roleUpdateDTO.getRoles().stream().map(roleName -> UserRole.valueOf(roleName)).collect(Collectors.toList()));
        return userProfile;
    }
}
