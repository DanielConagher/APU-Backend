package com.APU_Backend.main.market.domain.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.APU_Backend.main.market.domain.dto.MapaAprendizajeDTO;
import com.APU_Backend.main.market.domain.dto.MapaPersonalizadoDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class MapaRepositoryImpl
                implements MapaRepository {

        @PersistenceContext
        private EntityManager entityManager;

        /**
         * Método reutilizable para convertir el resultado del SP en DTOs.
         */
        private List<MapaAprendizajeDTO> obtenerMapa(
                        Integer idEstudiante,
                        Integer idTipoDesastre,
                        String storedProcedure) {

                List<Object[]> resultados = entityManager
                                .createNativeQuery(
                                                "CALL " + storedProcedure + "(:idEstudiante,:idTipoDesastre)")
                                .setParameter("idEstudiante", idEstudiante)
                                .setParameter("idTipoDesastre", idTipoDesastre)
                                .getResultList();

                List<MapaAprendizajeDTO> respuesta = new ArrayList<>();

                for (Object[] fila : resultados) {

                        respuesta.add(

                                        new MapaAprendizajeDTO(
                                                        ((Number) fila[0]).intValue(), // numeroNivel
                                                        ((Number) fila[1]).intValue(), // posicion
                                                        ((Number) fila[2]).intValue(), // idContenido
                                                        ((Number) fila[4]).intValue(), // estado
                                                        (String) fila[3], // titulo
                                                        (Boolean) fila[5] // esCuestionario
                                        )

                        );
                }

                return respuesta;
        }

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

                return obtenerMapa(
                                idEstudiante,
                                idTipoDesastre,
                                "sp_mapa_aprendizaje");
        }

        @Override
        @Transactional
        public List<MapaPersonalizadoDTO> obtenerMapaPersonalizado(
                        Integer idEstudiante,
                        Integer idTipoDesastre) {

                Number cantidad = (Number) entityManager
                                .createNativeQuery(
                                                "CALL sp_tiene_progreso_personalizado(:idEstudiante,:idTipoDesastre)")
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
                                                        "CALL sp_primera_burbuja_personalizada(:idEstudiante,:idTipoDesastre)")
                                        .setParameter(
                                                        "idEstudiante",
                                                        idEstudiante)
                                        .setParameter(
                                                        "idTipoDesastre",
                                                        idTipoDesastre)
                                        .getSingleResult())
                                        .intValue();

                        entityManager
                                        .createNativeQuery(
                                                        "CALL sp_crear_progreso_personalizado_inicial(:idEstudiante,:idContenido)")
                                        .setParameter(
                                                        "idEstudiante",
                                                        idEstudiante)
                                        .setParameter(
                                                        "idContenido",
                                                        primeraBurbuja)
                                        .executeUpdate();

                }

                List<Object[]> resultados = entityManager
                                .createNativeQuery(
                                                "CALL sp_mapa_personalizado(:idEstudiante,:idTipoDesastre)")
                                .setParameter(
                                                "idEstudiante",
                                                idEstudiante)
                                .setParameter(
                                                "idTipoDesastre",
                                                idTipoDesastre)
                                .getResultList();

                List<MapaPersonalizadoDTO> respuesta = new ArrayList<>();

                for (Object[] fila : resultados) {

                        respuesta.add(
                                        new MapaPersonalizadoDTO(

                                                        ((Number) fila[0]).intValue(), // nivel

                                                        ((Number) fila[2]).intValue(), // id contenido

                                                        ((Number) fila[4]).intValue(), // estado

                                                        (String) fila[3], // titulo

                                                        (Boolean) fila[5], // cuestionario

                                                        ((Number) fila[1]).intValue() // posicion

                                        ));

                }

                return respuesta;

        }

}