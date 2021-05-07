package com.egehan.basketballapi.dto;

import com.egehan.basketballapi.entity.Player;
import com.egehan.basketballapi.entity.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerCreateDto {

    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotNull
    private Position position;

    public Player toEntity() {
        return Player.builder()
                .name(name)
                .surname(surname)
                .position(position).build();
    }
}
