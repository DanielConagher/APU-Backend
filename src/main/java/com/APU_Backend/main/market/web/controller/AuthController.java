package com.APU_Backend.main.market.web.controller;

import org.springframework.web.bind.annotation.*;

import com.APU_Backend.main.market.domain.dto.AuthResponseDTO;
import com.APU_Backend.main.market.domain.dto.LoginDTO;
import com.APU_Backend.main.market.domain.dto.RegistroDTO;
import com.APU_Backend.main.market.domain.service.CredencialesService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final CredencialesService credencialesService;

    @PostMapping("/register")
    public AuthResponseDTO registrar(
            @Valid @RequestBody RegistroDTO request) {
        return credencialesService.registrar(request);
    }

    @PostMapping("/login")
    public AuthResponseDTO login(
            @Valid @RequestBody LoginDTO request) {

        return credencialesService.login(request);
    }
}
