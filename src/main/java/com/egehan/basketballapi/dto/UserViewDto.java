package com.egehan.basketballapi.dto;

import com.egehan.basketballapi.entity.EnumRole;
import com.egehan.basketballapi.entity.Role;
import com.egehan.basketballapi.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserViewDto {
    private String username;
    private String email;
    private Set<EnumRole> roles;

    public static UserViewDto of(User user) {
        final Set<EnumRole> roles = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());

        return UserViewDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(roles).build();
    }
}
