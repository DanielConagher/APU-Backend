package com.APU_Backend.main.market.domain.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class ProgresoRepositoryImpl implements ProgresoRepository {

        @PersistenceContext
        private EntityManager entityManager;

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
}
