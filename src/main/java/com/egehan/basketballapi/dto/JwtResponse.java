package com.egehan.basketballapi.dto;

import com.egehan.basketballapi.entity.EnumRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtResponse {
    private final String type = "Bearer";
    private String token;
    private Long id;
    private String username;
    private String email;
    private List<EnumRole> roles;
}
