package com.APU_Backend.main.market.domain.service;

import com.APU_Backend.main.market.domain.dto.ActualizarMochilaGeneralDTO;
import com.APU_Backend.main.market.domain.dto.MochilaGeneralDTO;

public interface MochilaService {

    MochilaGeneralDTO obtenerMochilaGeneral(Integer idEstudiante);

    MochilaGeneralDTO guardarCambiosGeneral(
            Integer idEstudiante,
            ActualizarMochilaGeneralDTO request);

}