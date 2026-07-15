package com.APU_Backend.main.market.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.APU_Backend.main.market.domain.IA.GeminiService;
import com.APU_Backend.main.market.domain.dto.ResumenComentariosDTO;
import com.APU_Backend.main.market.domain.repository.ComentarioRepository;
import com.APU_Backend.main.market.persistance.entity.Comentario;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnalisisComentariosServiceImpl
                implements AnalisisComentariosService {

        private final ComentarioRepository comentarioRepository;

        private final GeminiService geminiService;

        private final JwtService jwtService;

        @Override
        public String analizarComentarios(
                        Integer idContenido,
                        String authHeader) {

                String token = authHeader.replace(
                                "Bearer ",
                                "");

                String rol = jwtService.extractRol(token);

                if (!"ADMINISTRADOR".equals(rol)) {

                        throw new RuntimeException(
                                        "Solo un administrador puede generar resúmenes.");

                }

                List<Comentario> comentarios = comentarioRepository.findByContenidoId(
                                idContenido);

                if (comentarios.isEmpty()) {

                        throw new RuntimeException(
                                        "Este contenido no tiene comentarios.");

                }

                String textoComentarios = comentarios.stream()

                                .map(Comentario::getDescripcion)

                                .collect(Collectors.joining("\n"));

                return geminiService.generarResumen(
                                textoComentarios);

        }

        private String formatearComentario(Comentario comentario) {

                return comentario.getDescripcion();

        }

}