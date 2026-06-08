package com.APU_Backend.main.market.domain.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.APU_Backend.main.market.domain.dto.MapaAprendizajeDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class MapaRepositoryImpl
                implements MapaRepository {

        @PersistenceContext
        private EntityManager entityManager;

        @Override
        @Transactional
        public List<MapaAprendizajeDTO> obtenerMapaAprendizaje(
                        Integer idEstudiante,
                        Integer idTipoDesastre) {

                Number cantidad = (Number) entityManager
                                .createNativeQuery(
                                                "CALL sp_tiene_progreso(:idEstudiante,:idTipoDesastre)")
                                .setParameter(
                                                "idEstudiante",
                                                idEstudiante)
                                .setParameter(
                                                "idTipoDesastre",
                                                idTipoDesastre)
                                .getSingleResult();

                if (cantidad.intValue() == 0) {

                        Integer primeraBurbuja = ((Number) entityManager
                                        .createNativeQuery(
                                                        "CALL sp_primera_burbuja(:idTipoDesastre)")
                                        .setParameter(
                                                        "idTipoDesastre",
                                                        idTipoDesastre)
                                        .getSingleResult())
                                        .intValue();

                        entityManager
                                        .createNativeQuery(
                                                        "CALL sp_crear_progreso_inicial(:idEstudiante,:idContenido)")
                                        .setParameter(
                                                        "idEstudiante",
                                                        idEstudiante)
                                        .setParameter(
                                                        "idContenido",
                                                        primeraBurbuja)
                                        .executeUpdate();
                }

                List<Object[]> resultados = entityManager
                                .createNativeQuery("CALL sp_mapa_aprendizaje(:idEstudiante,:idTipoDesastre)")
                                .setParameter("idEstudiante", idEstudiante)
                                .setParameter("idTipoDesastre", idTipoDesastre)
                                .getResultList();

                List<MapaAprendizajeDTO> respuesta = new ArrayList<>();

                for (Object[] fila : resultados) {

                        respuesta.add(
                                        new MapaAprendizajeDTO(
                                                        ((Number) fila[0]).intValue(), // numeroNivel
                                                        ((Number) fila[1]).intValue(), // idContenido
                                                        ((Number) fila[3]).intValue(), // estado
                                                        (String) fila[2] // titulo
                                        ));
                }

                return respuesta;
        }
}