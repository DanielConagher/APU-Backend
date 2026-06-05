package com.APU_Backend.main.market.domain.service;

import java.util.List;

import com.APU_Backend.main.market.domain.dto.ProgresoAprendizajeDTO;

public interface ProgresoService {

    List<ProgresoAprendizajeDTO> obtenerProgresoAprendizaje(
            Integer idEstudiante);
}