package com.egehan.basketballapi.service;

import com.egehan.basketballapi.dto.JwtResponse;
import com.egehan.basketballapi.dto.LoginDto;
import com.egehan.basketballapi.dto.UserCreateDto;
import com.egehan.basketballapi.dto.UserViewDto;
import com.egehan.basketballapi.entity.EnumRole;
import com.egehan.basketballapi.entity.Role;
import com.egehan.basketballapi.entity.User;
import com.egehan.basketballapi.repository.RoleRepository;
import com.egehan.basketballapi.repository.UserRepository;
import com.egehan.basketballapi.security.jwt.JwtUtil;
import com.egehan.basketballapi.service.abstarct.UserService;
import com.egehan.basketballapi.service.exception.EmailAlreadyRegisteredException;
import com.egehan.basketballapi.service.exception.NoRolesFoundException;
import com.egehan.basketballapi.service.exception.UsernameAlreadyRegisteredException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public UserViewDto register(UserCreateDto userCreateDto) {
        final String username = userCreateDto.getUsername();
        final String encodedPassword = passwordEncoder.encode(userCreateDto.getPassword());
        final String email = userCreateDto.getEmail();
        final Set<EnumRole> enumRoles = userCreateDto.getRoles();
        final Set<Role> roles = new HashSet<>();

        if (userRepository.existsByUsername(username))
            throw new UsernameAlreadyRegisteredException("Username is already taken!");

        if (userRepository.existsByEmail(email))
            throw new EmailAlreadyRegisteredException("Email is already taken!");

        if (enumRoles.isEmpty())
            throw new NoRolesFoundException("No roles found");

        for (EnumRole enumRole : enumRoles) {
            roles.add(roleRepository.findByName(enumRole)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found.")));
        }

        final User user = User.builder()
                .username(username)
                .email(email)
                .password(encodedPassword)
                .roles(roles).build();

        final User savedUser = userRepository.save(user);
        return UserViewDto.of(savedUser);
    }

    @Override
    public JwtResponse login(LoginDto loginDto) {
        final String username = loginDto.getUsername();
        final String password = loginDto.getPassword();

        Authentication authentication
                = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<EnumRole> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .map(EnumRole::valueOf)
                .collect(Collectors.toList());

        return JwtResponse.builder()
                .id(userDetails.getId())
                .username(userDetails.getUsername())
                .email(userDetails.getEmail())
                .roles(roles)
                .token(jwt).build();
    }
}
