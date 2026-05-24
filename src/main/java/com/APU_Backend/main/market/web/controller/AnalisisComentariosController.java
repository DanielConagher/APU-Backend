package com.APU_Backend.main.market.web.controller;

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

    @PostMapping("/{idContenido}")
    public ResponseEntity<String> analizarComentarios(
            @PathVariable Integer idContenido) {

        try {

            String resumen = analisisService
                    .analizarComentarios(idContenido);

            return ResponseEntity.ok(resumen);

        } catch (Exception e) {

            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
}
