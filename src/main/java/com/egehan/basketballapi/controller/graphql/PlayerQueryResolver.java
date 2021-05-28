package com.egehan.basketballapi.controller.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.egehan.basketballapi.dto.PlayerViewDto;
import com.egehan.basketballapi.service.abstarct.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PlayerQueryResolver implements GraphQLQueryResolver {
    private final PlayerService playerService;

    @PreAuthorize("hasRole('ROLE_PLAYER') or hasRole('ROLE_COACH')")
    public List<PlayerViewDto> getAll() {
        return playerService.findAll();
    }

}
