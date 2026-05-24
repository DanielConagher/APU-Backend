package com.APU_Backend.main.market.web.controller;

import org.springframework.web.bind.annotation.*;

import com.APU_Backend.main.market.domain.dto.ContenidoDTO;
import com.APU_Backend.main.market.domain.service.ContenidoService;

@RestController
@RequestMapping("/contenido")
@CrossOrigin("*")
public class ContenidoController {

    private final ContenidoService contenidoService;

    public ContenidoController(
            ContenidoService contenidoService) {
        this.contenidoService = contenidoService;
    }

    @GetMapping("/{id}")
    public ContenidoDTO obtenerContenido(
            @PathVariable int id) {

        return contenidoService.obtenerContenido(id);
    }
}