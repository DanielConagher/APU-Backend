package com.APU_Backend.main.market.domain.repository;

public interface ProgresoRepository {

    void completarContenido(
            Integer idEstudiante,
            Integer idContenido);
}
