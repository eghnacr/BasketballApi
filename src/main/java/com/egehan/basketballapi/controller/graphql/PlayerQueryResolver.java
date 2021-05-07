package com.egehan.basketballapi.controller.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.egehan.basketballapi.dto.PlayerViewDto;
import com.egehan.basketballapi.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PlayerQueryResolver implements GraphQLQueryResolver {
    private final PlayerService playerService;

    public List<PlayerViewDto> getAll() {
        return playerService.findAll();
    }

}
