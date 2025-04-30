package com.wheel.safe.wheelsafe.user.dto;

import com.wheel.safe.wheelsafe.user.entity.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDTO {
    private Long id;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Email(message = "Email must be valid")
    private String email;

    @Pattern(regexp = "^[+]?[(]?[0-9]{1,4}[)]?[-\\s./0-9]*$", message = "Phone number format is invalid")
    private String phoneNumber;

    private UserRole role;
    private boolean mfaEnabled;
}
