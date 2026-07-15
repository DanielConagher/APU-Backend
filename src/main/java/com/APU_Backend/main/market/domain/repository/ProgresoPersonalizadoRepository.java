package com.APU_Backend.main.market.domain.repository;

public interface ProgresoPersonalizadoRepository {
    void completarContenido(
            Integer idEstudiante,
            Integer idContenidoPersonalizado);
}