package com.APU_Backend.main.market.domain.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProgresoPersonalizadoRepositoryImpl
        implements ProgresoPersonalizadoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void completarContenido(
            Integer idEstudiante,
            Integer idContenidoPersonalizado) {

        entityManager.createNativeQuery(
                "CALL sp_completar_contenido_personalizado(:idEstudiante,:idContenido)")

                .setParameter(
                        "idEstudiante",
                        idEstudiante)

                .setParameter(
                        "idContenido",
                        idContenidoPersonalizado)

                .executeUpdate();

    }

}