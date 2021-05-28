package com.egehan.basketballapi.service;

import com.egehan.basketballapi.dto.UserCreateDto;
import com.egehan.basketballapi.entity.EnumRole;
import com.egehan.basketballapi.repository.UserRepository;
import com.egehan.basketballapi.service.exception.EmailAlreadyRegisteredException;
import com.egehan.basketballapi.service.exception.NoRolesFoundException;
import com.egehan.basketballapi.service.exception.UsernameAlreadyRegisteredException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void registerWithExistUsername() {
        when(userRepository.existsByUsername(any(String.class))).thenReturn(true);
        UserCreateDto userCreateDto = UserCreateDto.builder()
                .username("Test-Username")
                .email("Test-Email")
                .password("Test-Password")
                .roles(Set.of(EnumRole.ROLE_PLAYER)).build();

        assertThrows(UsernameAlreadyRegisteredException.class, () ->
                userService.register(userCreateDto));
    }

    @Test
    void registerWithExistEmail() {
        when(userRepository.existsByEmail(any(String.class))).thenReturn(true);
        UserCreateDto userCreateDto = UserCreateDto.builder()
                .username("Test-Username")
                .email("Test-Email")
                .password("Test-Password")
                .roles(Set.of(EnumRole.ROLE_PLAYER)).build();

        assertThrows(EmailAlreadyRegisteredException.class, () ->
                userService.register(userCreateDto));
    }

    @Test
    void registerWithEmptyRoles() {
        UserCreateDto userCreateDto = UserCreateDto.builder()
                .username("Test-Username")
                .email("Test-Email")
                .password("Test-Password")
                .roles(Set.of()).build();


        assertThrows(NoRolesFoundException.class, () ->
                userService.register(userCreateDto));
    }

    @Test
    void login() {
    }
}