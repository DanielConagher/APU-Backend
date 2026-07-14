package com.APU_Backend.main.market.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.APU_Backend.main.market.domain.dto.ActualizarMaterialPersonalizadoDTO;
import com.APU_Backend.main.market.domain.dto.CrearMaterialPersonalizadoDTO;
import com.APU_Backend.main.market.domain.dto.MochilaPersonalizadaDTO;
import com.APU_Backend.main.market.domain.service.MochilaPersonalizadaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/mochila-personalizada")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MochilaPersonalizadaController {

        private final MochilaPersonalizadaService mochilaPersonalizadaService;

        @GetMapping("/{idEstudiante}")
        public ResponseEntity<MochilaPersonalizadaDTO> obtenerMochila(
                        @PathVariable Integer idEstudiante) {

                return ResponseEntity.ok(
                                mochilaPersonalizadaService.obtenerMochilaPersonalizada(idEstudiante));
        }

        @PostMapping("/{idEstudiante}/material")
        public ResponseEntity<MochilaPersonalizadaDTO> agregarMaterial(
                        @PathVariable Integer idEstudiante,
                        @RequestBody CrearMaterialPersonalizadoDTO request) {

                return ResponseEntity.ok(
                                mochilaPersonalizadaService.agregarMaterial(idEstudiante, request));
        }

        @PutMapping("/material/{idMaterial}")
        public ResponseEntity<MochilaPersonalizadaDTO> actualizarMaterial(
                        @PathVariable Integer idMaterial,
                        @RequestBody ActualizarMaterialPersonalizadoDTO request) {

                return ResponseEntity.ok(
                                mochilaPersonalizadaService.actualizarMaterial(idMaterial, request));
        }

        @DeleteMapping("/material/{idMaterial}")
        public ResponseEntity<MochilaPersonalizadaDTO> eliminarMaterial(
                        @PathVariable Integer idMaterial) {

                return ResponseEntity.ok(
                                mochilaPersonalizadaService.eliminarMaterial(idMaterial));
        }

}