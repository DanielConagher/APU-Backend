package com.APU_Backend.main.market.web.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import com.APU_Backend.main.market.domain.dto.CrearComentarioDTO;
import com.APU_Backend.main.market.domain.service.ComentarioService;

@RestController
@RequestMapping("/comentario")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ComentarioController {

    private final ComentarioService comentarioService;

    @PostMapping("/{idContenido}")
    public void publicarComentario(

            @PathVariable Integer idContenido,

            @RequestBody CrearComentarioDTO dto,

            @RequestHeader("Authorization") String authHeader) {

        comentarioService.publicarComentario(
                idContenido,
                dto.getComentario(),
                authHeader);
    }
}