package com.APU_Backend.main.market.web.controller;

import org.springframework.web.bind.annotation.*;

import com.APU_Backend.main.market.domain.dto.*;
import com.APU_Backend.main.market.domain.service.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/perfil")
@RequiredArgsConstructor
public class PerfilController {

    private final PerfilService perfilService;

    private final JwtService jwtService;

    @GetMapping
    public PerfilDTO obtenerPerfil(

            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.replace(
                "Bearer ",
                "");

        Integer idEstudiante = jwtService.extractId(
                token);

        return perfilService.obtenerPerfil(
                idEstudiante);
    }

    @PutMapping
    public PerfilDTO actualizarPerfil(

            @Valid @RequestBody ActualizarPerfilDTO request,

            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.replace(
                "Bearer ",
                "");

        Integer idEstudiante = jwtService.extractId(
                token);

        return perfilService.actualizarPerfil(
                idEstudiante,
                request);
    }
}