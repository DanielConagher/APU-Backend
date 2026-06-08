package com.APU_Backend.main.market.web.controller;

import org.springframework.web.bind.annotation.*;

import com.APU_Backend.main.market.domain.dto.CuestionarioDTO;
import com.APU_Backend.main.market.domain.dto.ResolverCuestionarioDTO;
import com.APU_Backend.main.market.domain.dto.ResultadoCuestionarioDTO;
import com.APU_Backend.main.market.domain.service.CuestionarioService;
import com.APU_Backend.main.market.domain.service.JwtService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cuestionarios")
@RequiredArgsConstructor
public class CuestionarioController {

        private final CuestionarioService cuestionarioService;
        private final JwtService jwtService;

        /*
         * Este endpoint obtiene el contenido de una burbuja que es de tipo cuestionario
         * (preguntas y respuestas)
         */
        @GetMapping("/contenido/{idContenido}")
        public CuestionarioDTO obtenerCuestionario(
                        @PathVariable Integer idContenido,
                        @RequestHeader("Authorization") String authHeader) {

                String token = authHeader.replace(
                                "Bearer ",
                                "");

                jwtService.extractId(token);

                return cuestionarioService
                                .obtenerPorContenido(idContenido);
        }

        /*
        Este endpoint recibe las respuestas elegidas por un estudiante en una burbuja de tipo cuestionario, 
        calcula su nota, y guarda su resultado en la tabla resultado_cuestionario. Tambien, desbloquea la siguiente
        burbuja del mapa. 
        */
        @PostMapping("/resolver")
        public ResultadoCuestionarioDTO resolver(

                        @RequestBody ResolverCuestionarioDTO request,

                        @RequestHeader("Authorization") String authHeader) {

                String token = authHeader.replace(
                                "Bearer ",
                                "");

                Integer idEstudiante = jwtService.extractId(
                                token);

                return cuestionarioService
                                .resolverCuestionario(
                                                idEstudiante,
                                                request);
        }
}