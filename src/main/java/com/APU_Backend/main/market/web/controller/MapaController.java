package com.APU_Backend.main.market.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.APU_Backend.main.market.domain.dto.MapaAprendizajeDTO;
import com.APU_Backend.main.market.domain.service.JwtService;
import com.APU_Backend.main.market.domain.service.MapaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/mapa")
@RequiredArgsConstructor
public class MapaController {

    private final MapaService mapaService;
    private final JwtService jwtService;

    /*
     * Este endpoint te devuelve todas las burbujas de un mapa de aprendizaje de un
     * tipo especifico de desastre natural
     * junto al estado de cada burbuja de un estudiante de id especifico (0:
     * Bloqueado, 1: Completado, 2: En proceso).
     * Cuando el estudiante ingresa por primera vez al mapa de un tipo de desastre
     * especifico, este endpoint hace que
     * se cree un nuevo registro en la tabla Progreso donde ponga la primera burbuja
     * del mapa en estado de En proceso (2)
     * para que, en el frontend, el estudiante pueda comenzar desde esa burbuja.
     */
    @GetMapping("/{idTipoDesastre}")
    public List<MapaAprendizajeDTO> obtenerMapaAprendizaje(

            @PathVariable Integer idTipoDesastre,

            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.replace(
                "Bearer ",
                "");

        Integer idEstudiante = jwtService.extractId(token);

        return mapaService
                .obtenerMapaAprendizaje(
                        idEstudiante,
                        idTipoDesastre);
    }
}