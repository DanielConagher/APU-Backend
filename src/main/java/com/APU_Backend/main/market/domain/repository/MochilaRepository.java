package com.APU_Backend.main.market.domain.repository;

import com.APU_Backend.main.market.domain.dto.ActualizarMochilaGeneralDTO;
import com.APU_Backend.main.market.domain.dto.MochilaGeneralDTO;

public interface MochilaRepository {

    MochilaGeneralDTO obtenerMochilaGeneral(Integer idEstudiante);

    MochilaGeneralDTO guardarCambiosGeneral(
            Integer idEstudiante,
            ActualizarMochilaGeneralDTO request);

}