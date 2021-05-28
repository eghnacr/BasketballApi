package com.egehan.basketballapi.service.abstarct;

import com.egehan.basketballapi.dto.JwtResponse;
import com.egehan.basketballapi.dto.LoginDto;
import com.egehan.basketballapi.dto.UserCreateDto;
import com.egehan.basketballapi.dto.UserViewDto;

public interface UserService {
    UserViewDto register(UserCreateDto userCreateDto);

    JwtResponse login(LoginDto loginDto);
}
