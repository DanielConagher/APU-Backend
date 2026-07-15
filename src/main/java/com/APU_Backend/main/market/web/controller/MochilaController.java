package com.APU_Backend.main.market.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.APU_Backend.main.market.domain.dto.ActualizarMochilaGeneralDTO;
import com.APU_Backend.main.market.domain.dto.MochilaGeneralDTO;
import com.APU_Backend.main.market.domain.service.MochilaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/mochila-general")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MochilaController {

    private final MochilaService mochilaService;

    @GetMapping("/{idEstudiante}")
    public ResponseEntity<MochilaGeneralDTO> obtenerMochila(
            @PathVariable Integer idEstudiante) {

        return ResponseEntity.ok(
                mochilaService.obtenerMochilaGeneral(idEstudiante));

    }

    @PutMapping("/{idEstudiante}")
    public ResponseEntity<MochilaGeneralDTO> actualizarMochila(
            @PathVariable Integer idEstudiante,
            @RequestBody ActualizarMochilaGeneralDTO request) {

        return ResponseEntity.ok(
                mochilaService.guardarCambiosGeneral(
                        idEstudiante,
                        request));

    }

}
