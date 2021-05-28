package com.egehan.basketballapi.dto;

import com.egehan.basketballapi.entity.EnumRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreateDto {
    @NotEmpty
    @Size(min = 6)
    private String username;

    @Email
    private String email;

    @NotEmpty
    @Size(min = 8)
    private String password;

    private Set<EnumRole> roles;
}
