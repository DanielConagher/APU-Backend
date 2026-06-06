package com.APU_Backend.main.market.domain.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.APU_Backend.main.market.domain.dto.*;
import com.APU_Backend.main.market.persistance.crud.ContenidoCrudRepository;
import com.APU_Backend.main.market.persistance.crud.CuestionarioCrudRepository;
import com.APU_Backend.main.market.persistance.crud.PreguntaCrudRepository;
import com.APU_Backend.main.market.persistance.crud.RespuestaCrudRepository;
import com.APU_Backend.main.market.persistance.crud.ResultadoCuestionarioCrudRepository;
import com.APU_Backend.main.market.persistance.entity.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CuestionarioRepositoryImpl
                implements CuestionarioRepository {

        private final ContenidoCrudRepository contenidoRepo;

        private final RespuestaCrudRepository respuestaRepo;

        private final CuestionarioCrudRepository cuestionarioRepo;

        private final PreguntaCrudRepository preguntaRepo;

        @PersistenceContext
        private EntityManager entityManager;

        @Override
        public CuestionarioDTO obtenerPorContenido(
                        Integer idContenido) {

                Contenido contenido = contenidoRepo.findById(idContenido)
                                .orElseThrow(() -> new RuntimeException(
                                                "Contenido no encontrado"));

                if (!contenido.getEsCuestionario()) {

                        throw new RuntimeException(
                                        "El contenido no es un cuestionario");
                }

                Cuestionario cuestionario = contenido.getCuestionario();

                List<PreguntaDTO> preguntasDTO = new ArrayList<>();

                for (Pregunta pregunta : cuestionario.getPregunta()) {

                        List<RespuestaDTO> respuestasDTO = new ArrayList<>();

                        for (Respuesta respuesta : pregunta.getRespuestas()) {

                                respuestasDTO.add(
                                                new RespuestaDTO(
                                                                respuesta.getIdRespuesta(),
                                                                respuesta.getDescripcion()));
                        }

                        preguntasDTO.add(
                                        new PreguntaDTO(
                                                        pregunta.getIdPregunta(),
                                                        pregunta.getDescripcion(),
                                                        respuestasDTO));
                }

                return new CuestionarioDTO(
                                cuestionario.getIdCuestionario(),
                                cuestionario.getExperienciaGanada(),
                                cuestionario.getRetroalimentacion(),
                                cuestionario.getTipo(),
                                cuestionario.getNumDesastres(),
                                preguntasDTO);
        }

        @Override
        public ResultadoCuestionarioDTO resolverCuestionario(
                        Integer idEstudiante,
                        ResolverCuestionarioDTO request) {

                Cuestionario cuestionario = cuestionarioRepo.findById(
                                request.getIdCuestionario())
                                .orElseThrow(() -> new RuntimeException(
                                                "Cuestionario no encontrado"));

                int correctas = 0;

                for (RespuestaUsuarioDTO respuestaUsuario : request.getRespuestas()) {

                        // Validar que la pregunta pertenezca al cuestionario
                        preguntaRepo.findByIdPreguntaAndCuestionario_IdCuestionario(
                                        respuestaUsuario.getIdPregunta(),
                                        request.getIdCuestionario())
                                        .orElseThrow(() -> new RuntimeException(
                                                        "La pregunta no pertenece al cuestionario"));

                        // Validar que la respuesta pertenezca a la pregunta
                        Respuesta respuesta = respuestaRepo
                                        .findByIdRespuestaAndPregunta_IdPregunta(
                                                        respuestaUsuario.getIdRespuestaSeleccionada(),
                                                        respuestaUsuario.getIdPregunta())
                                        .orElseThrow(() -> new RuntimeException(
                                                        "La respuesta no pertenece a la pregunta"));

                        if (Boolean.TRUE.equals(
                                        respuesta.getEsCorrecta())) {

                                correctas++;
                        }
                }

                int totalPreguntas = request.getRespuestas().size();

                int nota = (correctas * 20)
                                / totalPreguntas;

                entityManager
                                .createNativeQuery(
                                                "CALL sp_resolver_cuestionario(:idEstudiante,:idCuestionario,:nota,:idContenido)")
                                .setParameter(
                                                "idEstudiante",
                                                idEstudiante)
                                .setParameter(
                                                "idCuestionario",
                                                request.getIdCuestionario())
                                .setParameter(
                                                "nota",
                                                nota)
                                .setParameter(
                                                "idContenido",
                                                request.getIdContenido())
                                .executeUpdate();

                return new ResultadoCuestionarioDTO(
                                nota,
                                cuestionario.getExperienciaGanada(),
                                cuestionario.getRetroalimentacion());
        }

}