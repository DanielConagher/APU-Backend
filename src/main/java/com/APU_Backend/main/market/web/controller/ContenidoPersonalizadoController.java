package com.APU_Backend.main.market.web.controller;

import org.springframework.web.bind.annotation.*;

import com.APU_Backend.main.market.domain.dto.ContenidoPersonalizadoDTO;
import com.APU_Backend.main.market.domain.service.ContenidoPersonalizadoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/contenido-personalizado")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ContenidoPersonalizadoController {

    private final ContenidoPersonalizadoService service;

    @GetMapping("/{id}")
    public ContenidoPersonalizadoDTO obtenerContenido(

            @PathVariable Integer id

    ) {

        return service.obtenerContenido(id);

    }

}