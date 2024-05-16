package com.josecondori.exchangeapi.application.controllers;

import com.josecondori.exchangeapi.infrastructure.db.springdata.dbo.entities.Role;
import com.josecondori.exchangeapi.infrastructure.db.springdata.dbo.entities.UserEntity;
import com.josecondori.exchangeapi.domain.models.enums.RoleEnum;
import com.josecondori.exchangeapi.infrastructure.rest.spring.dto.request.UserDTO;
import com.josecondori.exchangeapi.application.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/user")
@PreAuthorize("hasRole('ADMIN')")
public class PrincipalController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(path = "/create")
    public ResponseEntity<UserEntity> createUser(@Valid @RequestBody UserDTO userDto) {
        Set<Role> roles = userDto.getRoles().stream()
                .map(rol -> Role.builder()
                        .name(RoleEnum.valueOf(rol))
                        .build())
                .collect(Collectors.toSet());
        UserEntity user = UserEntity.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .email(userDto.getEmail())
                .roles(roles)
                .build();

        userRepository.save(user);

        return ResponseEntity.ok(user);
    }

    @DeleteMapping(path = "/delete")
    public String deleteUser(@RequestParam String id) {
        userRepository.deleteById(Long.parseLong(id));
        return "Se ha borrado el user con id %s".formatted(id);
    }
}
