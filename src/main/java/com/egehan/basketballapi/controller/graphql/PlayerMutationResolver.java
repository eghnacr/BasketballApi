package com.egehan.basketballapi.controller.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.egehan.basketballapi.dto.PlayerCreateDto;
import com.egehan.basketballapi.dto.PlayerViewDto;
import com.egehan.basketballapi.service.PlayerService;
import com.egehan.basketballapi.service.PlayerServiceImpl;
import graphql.execution.DataFetcherResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@RequiredArgsConstructor
@Component
@Validated
public class PlayerMutationResolver implements GraphQLMutationResolver {
    private final PlayerService playerService;

    public PlayerViewDto createPlayer(@Valid PlayerCreateDto playerCreateDto){
        return playerService.save(playerCreateDto);
    }

    public void deletePlayer(Long id){
        playerService.delete(id);
    }
}
