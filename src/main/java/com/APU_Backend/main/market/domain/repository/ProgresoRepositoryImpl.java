package com.APU_Backend.main.market.domain.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.APU_Backend.main.market.domain.dto.ProgresoAprendizajeDTO;
import com.APU_Backend.main.market.persistance.crud.EstudianteCrudRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProgresoRepositoryImpl implements ProgresoRepository {

        @PersistenceContext
        private EntityManager entityManager;

        private final EstudianteCrudRepository estudianteRepo;

        @Override
        @Transactional
        public void completarContenido(
                        Integer idEstudiante,
                        Integer idContenido) {

                entityManager.createNativeQuery(
                                "CALL sp_completar_contenido(:idEstudiante,:idContenido)")
                                .setParameter(
                                                "idEstudiante",
                                                idEstudiante)
                                .setParameter(
                                                "idContenido",
                                                idContenido)
                                .executeUpdate();
        }

        @Override
        public List<ProgresoAprendizajeDTO> obtenerProgresoAprendizaje(
                        Integer idEstudiante) {

                if (idEstudiante == null) {
                        throw new RuntimeException(
                                        "Id de estudiante inválido");
                }

                List<Object[]> resultados;

                if (!estudianteRepo.existsById(idEstudiante)) {

                        throw new RuntimeException(
                                        "Estudiante no encontrado");
                }

                try {

                        resultados = entityManager
                                        .createNativeQuery(
                                                        "CALL sp_progreso_aprendizaje(:id)")
                                        .setParameter(
                                                        "id",
                                                        idEstudiante)
                                        .getResultList();

                } catch (Exception e) {

                        throw new RuntimeException(
                                        "Error al obtener el progreso de aprendizaje");
                }

                List<ProgresoAprendizajeDTO> respuesta = new ArrayList<>();

                for (Object[] fila : resultados) {

                        respuesta.add(
                                        new ProgresoAprendizajeDTO(
                                                        ((Number) fila[0]).intValue(),
                                                        (String) fila[1],
                                                        ((Number) fila[2]).doubleValue()));
                }

                return respuesta;
        }
}
