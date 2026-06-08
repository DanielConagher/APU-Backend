package com.APU_Backend.main.market.domain.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.APU_Backend.main.market.domain.repository.ComentarioRepository;
import com.APU_Backend.main.market.domain.repository.ContenidoRepository;
import com.APU_Backend.main.market.domain.repository.EstudianteRepository;
import com.APU_Backend.main.market.persistance.entity.Comentario;
import com.APU_Backend.main.market.persistance.entity.Contenido;
import com.APU_Backend.main.market.persistance.entity.Estudiante;
import com.APU_Backend.main.market.domain.service.JwtService;

@Service
@RequiredArgsConstructor
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;

    private final ContenidoRepository contenidoRepository;

    private final EstudianteRepository estudianteRepository;

    private final JwtService jwtService;

    public void publicarComentario(

            Integer idContenido,

            String texto,

            String authHeader) {

        String token = authHeader.replace(
                "Bearer ",
                "");

        Integer idEstudiante = jwtService.extractId(token);

        Estudiante estudiante = estudianteRepository.findById(
                idEstudiante)
                .orElseThrow();

        Contenido contenido = contenidoRepository.getContenido(
                idContenido)
                .orElseThrow();

        Comentario comentario = new Comentario();

        comentario.setFecha(
                LocalDateTime.now());

        comentario.setDescripcion(
                texto);

        comentario.setEstudiante(
                estudiante);

        comentario.setContenido(
                contenido);

        comentarioRepository.guardar(
                comentario);
    }
}