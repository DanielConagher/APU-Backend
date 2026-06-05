package com.APU_Backend.main.market.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.APU_Backend.main.market.domain.dto.ProgresoAprendizajeDTO;
import com.APU_Backend.main.market.domain.service.JwtService;
import com.APU_Backend.main.market.domain.service.ProgresoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/progreso")
@RequiredArgsConstructor
public class ProgresoController {

    private final ProgresoService progresoService;

    private final JwtService jwtService;

    // Controller para obtener el progreso de un estudiante especifico de un tipo de
    // desastre especifico. Para que este endpoint devuelva un tipo de desastre con
    // su porcentaje completado del estudiante
    // debe existir, en la tabla de contenido, contenidos (burbujas) de ese tipo de
    // desastre especifico. Sino,
    // no devolvera ese tipo de desastre en la respuesta.
    @GetMapping
    public List<ProgresoAprendizajeDTO> obtenerProgresoAprendizaje(

            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.replace(
                "Bearer ",
                "");

        Integer idEstudiante = jwtService.extractUserId(
                token);

        return progresoService
                .obtenerProgresoAprendizaje(
                        idEstudiante);
    }
}