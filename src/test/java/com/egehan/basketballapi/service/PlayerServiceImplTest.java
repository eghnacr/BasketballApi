package com.egehan.basketballapi.service;

import com.egehan.basketballapi.dto.PlayerCreateDto;
import com.egehan.basketballapi.dto.PlayerViewDto;
import com.egehan.basketballapi.entity.Player;
import com.egehan.basketballapi.entity.Position;
import com.egehan.basketballapi.repository.PlayerRepository;
import com.egehan.basketballapi.service.exception.TeamIsFullException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

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
        Player player = Player.builder()
                .id(1L)
                .name("Test-Name")
                .surname("Test-Surname")
                .position(Position.CENTER).build();
        when(playerRepository.findAll()).thenReturn(List.of(player));

        List<PlayerViewDto> playerViewDtos = playerService.findAll();

        assertEquals(playerViewDtos.size(), 1);
        assertEquals(playerViewDtos.get(0).getId(), player.getId());
        assertEquals(playerViewDtos.get(0).getName(), player.getName());
    }

    @Test
    void testSave() {
        PlayerCreateDto playerCreateDto = PlayerCreateDto.builder()
                .name("Test-Name")
                .surname("Test-Surname")
                .position(Position.SHOOTING_GUARD).build();
        when(playerRepository.save(any(Player.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        PlayerViewDto playerViewDto = playerService.save(playerCreateDto);

        assertEquals(playerCreateDto.getName(), playerViewDto.getName());
    }

    @Test
    void testSaveWhenTeamIsFull() {
        PlayerCreateDto playerCreateDto = PlayerCreateDto.builder().build();

        when(playerRepository.count()).thenReturn(5L);

        assertThrows(TeamIsFullException.class, () ->
                playerService.save(playerCreateDto)
        );
    }

    @Test
    void delete() {
        Long id = 1L;
        playerService.delete(id);
        verify(playerRepository, times(1)).deleteById(id);
    }
}