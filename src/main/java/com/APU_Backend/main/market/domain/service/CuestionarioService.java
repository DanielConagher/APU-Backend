package com.APU_Backend.main.market.domain.service;

import com.APU_Backend.main.market.domain.dto.CuestionarioDTO;
import com.APU_Backend.main.market.domain.dto.ResolverCuestionarioDTO;
import com.APU_Backend.main.market.domain.dto.ResultadoCuestionarioDTO;

public interface CuestionarioService {

    CuestionarioDTO obtenerPorContenido(
            Integer idContenido);

    ResultadoCuestionarioDTO resolverCuestionario(
            Integer idEstudiante,
            ResolverCuestionarioDTO request);
}
