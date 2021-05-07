package com.egehan.basketballapi.dto;

import com.egehan.basketballapi.entity.Player;
import com.egehan.basketballapi.entity.Position;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerViewDto {
    private Long id;
    private String name;
    private String surname;
    private Position position;

    public static PlayerViewDto of(Player player){
        return PlayerViewDto.builder()
                .id(player.getId())
                .name(player.getName())
                .surname(player.getSurname())
                .position(player.getPosition()).build();
    }
}
