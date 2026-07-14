package com.APU_Backend.main.market.web.controller;

import org.springframework.web.bind.annotation.*;

import com.APU_Backend.main.market.domain.service.JwtService;
import com.APU_Backend.main.market.domain.service.ProgresoPersonalizadoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/progreso-personalizado")
@RequiredArgsConstructor
public class ProgresoPersonalizadoController {

    private final ProgresoPersonalizadoService service;

    private final JwtService jwtService;

    @PostMapping("/completar/{idContenido}")
    public void completarContenido(

            @PathVariable Integer idContenido,

            @RequestHeader("Authorization") String authHeader

    ) {

        String token = authHeader.replace(
                "Bearer ",
                "");

        Integer idEstudiante = jwtService.extractId(token);

        service.completarContenido(
                idEstudiante,
                idContenido);

    }

}