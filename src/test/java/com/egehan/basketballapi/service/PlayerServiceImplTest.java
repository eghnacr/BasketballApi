package com.egehan.basketballapi.service;

import com.egehan.basketballapi.dto.PlayerCreateDto;
import com.egehan.basketballapi.dto.PlayerViewDto;
import com.egehan.basketballapi.entity.Player;
import com.egehan.basketballapi.entity.Position;
import com.egehan.basketballapi.repository.PlayerRepository;
import com.egehan.basketballapi.service.exception.TeamIsFullException;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerServiceImplTest {

    @InjectMocks
    private PlayerServiceImpl playerService;
    @Mock
    private PlayerRepository playerRepository;

    @Test
    void testFindAll() {

    }

    @Test
    void testSave() {
        PlayerCreateDto playerCreateDto = PlayerCreateDto.builder()
                .name("Test-Name")
                .surname("Test-Surname")
                .position(Position.SHOOTING_GUARD).build();

        Player mockPlayer = mock(Player.class);
        when(mockPlayer.getId())
                .thenReturn(1L);
        when(playerRepository.save(any(Player.class)))
                .thenReturn(mockPlayer);

        /*when(mockPlayer.getName())
                .thenReturn("Test-Name");*/

        PlayerViewDto playerViewDto = playerService.save(playerCreateDto);

        assertEquals(playerCreateDto.getName(), playerViewDto.getName());
        assertEquals(playerViewDto.getId(), 1L);
    }

    @Test
    void testSaveWhenTeamIsFull() {
        PlayerCreateDto playerCreateDto = PlayerCreateDto.builder()
                .name("Test-Name")
                .surname("Test-Surname")
                .position(Position.SHOOTING_GUARD).build();

        Player mockPlayer = mock(Player.class);
        when(playerRepository.save(any(Player.class)))
                .thenReturn(mockPlayer);

        assertThrows(TeamIsFullException.class, () -> {
            for (int i = 0; i < 6; i++)
                playerService.save(playerCreateDto);
        });
    }

    @Test
    void delete() {

    }
}