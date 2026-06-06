package com.APU_Backend.main.market.domain.repository;

import java.util.List;

import com.APU_Backend.main.market.domain.dto.MapaAprendizajeDTO;

public interface MapaRepository {

    List<MapaAprendizajeDTO> obtenerMapaAprendizaje(
            Integer idEstudiante,
            Integer idTipoDesastre);
}