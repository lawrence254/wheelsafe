import jakarta.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleUpdateDTO {
    @NotBlank(message = "User ID is required")
    private Long userId;
    @NotBlank(message = "Roles are required")
    @Size(min = 1, message = "At least one role must be specified")
    private List<String> roles;

    public static RoleUpdateDTO fromUserProfileEntity(UserProfileDTO userProfile) {
        return RoleUpdateDTO.builder()
                .userId(userProfile.getId())
                .roles(userProfile.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList()))
                .build();
    }

    public static UserProfileDTO toUserProfileEntity(RoleUpdateDTO roleUpdateDTO) {
        UserProfileDTO userProfile = new UserProfileDTO();
        userProfile.setId(roleUpdateDTO.getUserId());
        userProfile.setRoles(roleUpdateDTO.getRoles().stream().map(roleName -> new UserRole(roleName)).collect(Collectors.toList()));
        return userProfile;
    }
}
