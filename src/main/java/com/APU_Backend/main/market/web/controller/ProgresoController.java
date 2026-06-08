package com.APU_Backend.main.market.web.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.APU_Backend.main.market.domain.dto.CompletarContenidoDTO;
import com.APU_Backend.main.market.domain.dto.ProgresoAprendizajeDTO;
import com.APU_Backend.main.market.domain.service.JwtService;
import com.APU_Backend.main.market.domain.service.ProgresoService;

import org.springframework.web.bind.annotation.RequestBody;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/progreso")
@RequiredArgsConstructor
public class ProgresoController {

        private final ProgresoService progresoService;
        private final JwtService jwtService;

        /*
         * Este endpoint recibe el id de un estudiante y el id de una burbuja(contenido)
         * especifica,
         * lo que hace es que cuando el estudiante completa una burbuja este endpoint
         * envia los dos valores a la bd
         * para que en la tabla progreso se guarde esa burbuja completada(1) para ese
         * estudiante y automaticamente
         * se cree un nuevo registro tambien con la siguiente burbuja de ese estudiante
         * con la columna "completado" como 2(en proceso)
         * para que en el frontend se desbloquee y pueda acceder a ella. Este endpoint
         * tambien considera si la burbuja es la ultima del
         * nivel para desbloquear la burbuja del siguiente nivel. Y, considera si el
         * nivel es el ultimo del mapa para no desploquear
         * otra más.
         */
        @PostMapping("/completar")
        public ResponseEntity<?> completarContenido(
                        @RequestBody CompletarContenidoDTO request,
                        @RequestHeader("Authorization") String authHeader) {

                String token = authHeader.replace(
                                "Bearer ",
                                "");

                Integer idEstudiante = jwtService.extractId(token);

                System.out.println(
                                "ESTUDIANTE = "
                                                + idEstudiante);

                progresoService.completarContenido(
                                idEstudiante,
                                request.getIdContenido());

                return ResponseEntity.ok(
                                "Contenido completado");
        }

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

                Integer idEstudiante = jwtService.extractId(
                                token);

                return progresoService
                                .obtenerProgresoAprendizaje(
                                                idEstudiante);
        }
}
