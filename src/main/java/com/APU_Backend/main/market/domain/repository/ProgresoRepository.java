package com.APU_Backend.main.market.domain.repository;

import java.util.List;

import com.APU_Backend.main.market.domain.dto.ProgresoAprendizajeDTO;

public interface ProgresoRepository {

    List<ProgresoAprendizajeDTO> obtenerProgresoAprendizaje(
            Integer idEstudiante);

}