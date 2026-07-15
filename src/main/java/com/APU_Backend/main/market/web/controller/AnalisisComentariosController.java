package com.APU_Backend.main.market.web.controller;

import com.APU_Backend.main.market.domain.dto.ResumenComentariosDTO;
import com.APU_Backend.main.market.domain.service.AnalisisComentariosService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/analisis")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AnalisisComentariosController {

    private final AnalisisComentariosService analisisService;

    @GetMapping("/{idContenido}")
    public ResponseEntity<String> analizarComentarios(

            @PathVariable Integer idContenido,

            @RequestHeader("Authorization") String authHeader) {

        try {

            String resumen = analisisService.analizarComentarios(
                    idContenido,
                    authHeader);

            return ResponseEntity.ok(resumen);

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());

        }

    }
}
