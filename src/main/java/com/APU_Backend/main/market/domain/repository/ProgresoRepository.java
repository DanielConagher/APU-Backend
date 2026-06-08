package com.APU_Backend.main.market.domain.repository;

import java.util.List;

import com.APU_Backend.main.market.domain.dto.ProgresoAprendizajeDTO;

public interface ProgresoRepository {

    void completarContenido(
            Integer idEstudiante,
            Integer idContenido);

    List<ProgresoAprendizajeDTO> obtenerProgresoAprendizaje(
            Integer idEstudiante);

}
