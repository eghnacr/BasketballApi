package com.egehan.basketballapi.service;

import com.egehan.basketballapi.dto.PlayerCreateDto;
import com.egehan.basketballapi.dto.PlayerViewDto;
import com.egehan.basketballapi.entity.Player;
import com.egehan.basketballapi.repository.PlayerRepository;
import com.egehan.basketballapi.service.abstarct.PlayerService;
import com.egehan.basketballapi.service.exception.TeamIsFullException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;

    @Transactional
    public List<PlayerViewDto> findAll() {
        return playerRepository.findAll()
                .stream()
                .map(PlayerViewDto::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public PlayerViewDto save(PlayerCreateDto playerCreateDto) {
        if (isTeamFull())
            throw new TeamIsFullException("Team is already full.");

        Player player = playerRepository.save(playerCreateDto.toEntity());
        return PlayerViewDto.of(player);

    }

    public boolean isTeamFull() {
        long numberOfPlayer = playerRepository.count();
        return numberOfPlayer >= 5;
    }

    @Transactional
    public void delete(Long id) {
        playerRepository.deleteById(id);
    }
}
