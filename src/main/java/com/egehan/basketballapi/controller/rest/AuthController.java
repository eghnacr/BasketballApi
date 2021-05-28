package com.egehan.basketballapi.controller.rest;

import com.egehan.basketballapi.dto.JwtResponse;
import com.egehan.basketballapi.dto.LoginDto;
import com.egehan.basketballapi.dto.UserCreateDto;
import com.egehan.basketballapi.dto.UserViewDto;
import com.egehan.basketballapi.service.abstarct.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginDto loginDto) {
        JwtResponse response = userService.login(loginDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserViewDto> registerUser(@Valid @RequestBody UserCreateDto userCreateDto) {
        UserViewDto userViewDto = userService.register(userCreateDto);
        return ResponseEntity.ok(userViewDto);
    }
}
