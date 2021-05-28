package com.egehan.basketballapi.service.abstarct;

import com.egehan.basketballapi.dto.PlayerCreateDto;
import com.egehan.basketballapi.dto.PlayerViewDto;

import java.util.List;

public interface PlayerService {
    List<PlayerViewDto> findAll();

    PlayerViewDto save(PlayerCreateDto playerCreateDto);

    void delete(Long id);
}
