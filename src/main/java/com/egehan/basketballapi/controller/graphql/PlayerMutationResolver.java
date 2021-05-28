package com.egehan.basketballapi.controller.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.egehan.basketballapi.dto.PlayerCreateDto;
import com.egehan.basketballapi.dto.PlayerViewDto;
import com.egehan.basketballapi.service.abstarct.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@RequiredArgsConstructor
@Component
@Validated
public class PlayerMutationResolver implements GraphQLMutationResolver {
    private final PlayerService playerService;

    @PreAuthorize("hasRole('ROLE_COACH')")
    public PlayerViewDto createPlayer(@Valid PlayerCreateDto playerCreateDto) {
        return playerService.save(playerCreateDto);
    }

    @PreAuthorize("hasRole('ROLE_COACH')")
    public void deletePlayer(Long id) {
        playerService.delete(id);
    }
}
