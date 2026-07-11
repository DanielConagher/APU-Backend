package com.APU_Backend.main.market.domain.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.APU_Backend.main.market.domain.dto.*;
import com.APU_Backend.main.market.persistance.crud.ContenidoCrudRepository;
import com.APU_Backend.main.market.persistance.crud.CuestionarioCrudRepository;
import com.APU_Backend.main.market.persistance.crud.PreguntaCrudRepository;
import com.APU_Backend.main.market.persistance.crud.ProgresoCrudRepository;
import com.APU_Backend.main.market.persistance.crud.RespuestaCrudRepository;
import com.APU_Backend.main.market.persistance.crud.ResultadoCuestionarioCrudRepository;
import com.APU_Backend.main.market.persistance.entity.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CuestionarioRepositoryImpl
                implements CuestionarioRepository {

        private final ContenidoCrudRepository contenidoRepo;

        private final RespuestaCrudRepository respuestaRepo;

        private final CuestionarioCrudRepository cuestionarioRepo;

        private final PreguntaCrudRepository preguntaRepo;

        private final ProgresoCrudRepository progresoRepo;

        private final ResultadoCuestionarioCrudRepository resultadoRepo;

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
                // Validamos si es que el estudiante realmente tenga desbloqueado el
                // cuestionario
                Progreso progreso = progresoRepo
                                .findByEstudiante_IdEstudianteAndContenido_IdContenido(
                                                idEstudiante,
                                                request.getIdContenido())
                                .orElseThrow(() -> new RuntimeException("Contenido bloqueado"));

                if (Integer.valueOf(1).equals(progreso.getCompletada())) {

                        throw new RuntimeException(
                                        "Este cuestionario ya fue aprobado.");

                }

                Optional<ResultadoCuestionario> resultadoAnterior = resultadoRepo
                                .findByEstudiante_IdEstudianteAndCuestionario_IdCuestionario(
                                                idEstudiante,
                                                request.getIdCuestionario());

                if (resultadoAnterior.isPresent()) {

                        if (resultadoAnterior.get().getNota() >= 16) {

                                return new ResultadoCuestionarioDTO(

                                                resultadoAnterior.get().getNota(),

                                                true,

                                                0,

                                                "Este cuestionario ya fue aprobado anteriormente."

                                );

                        }

                }

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

                int nota = (int) Math.round((correctas * 20.0) / totalPreguntas);

                StoredProcedureQuery sp = entityManager
                                .createStoredProcedureQuery("sp_resolver_cuestionario");

                sp.registerStoredProcedureParameter(
                                "p_id_estudiante",
                                Integer.class,
                                ParameterMode.IN);

                sp.registerStoredProcedureParameter(
                                "p_id_cuestionario",
                                Integer.class,
                                ParameterMode.IN);

                sp.registerStoredProcedureParameter(
                                "p_nota",
                                Integer.class,
                                ParameterMode.IN);

                sp.registerStoredProcedureParameter(
                                "p_id_contenido",
                                Integer.class,
                                ParameterMode.IN);

                sp.setParameter("p_id_estudiante", idEstudiante);
                sp.setParameter("p_id_cuestionario", request.getIdCuestionario());
                sp.setParameter("p_nota", nota);
                sp.setParameter("p_id_contenido", request.getIdContenido());

                sp.execute();

                boolean aprobado = nota >= 16;

                return new ResultadoCuestionarioDTO(nota,
                                aprobado,
                                aprobado ? cuestionario.getExperienciaGanada() : 0,
                                cuestionario.getRetroalimentacion());
        }

}