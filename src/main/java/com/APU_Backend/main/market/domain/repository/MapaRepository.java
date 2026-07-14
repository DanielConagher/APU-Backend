package com.APU_Backend.main.market.domain.repository;

import java.util.List;

import com.APU_Backend.main.market.domain.dto.MapaAprendizajeDTO;
import com.APU_Backend.main.market.domain.dto.MapaPersonalizadoDTO;

public interface MapaRepository {

        List<MapaAprendizajeDTO> obtenerMapaAprendizaje(
                        Integer idEstudiante,
                        Integer idTipoDesastre);

        List<MapaPersonalizadoDTO> obtenerMapaPersonalizado(
                        Integer idEstudiante,
                        Integer idTipoDesastre);
}