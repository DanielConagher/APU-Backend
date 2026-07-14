package com.APU_Backend.main.market.domain.service;

import com.APU_Backend.main.market.domain.dto.ContenidoPersonalizadoDTO;

public interface ContenidoPersonalizadoService {

    ContenidoPersonalizadoDTO obtenerContenido(
            Integer idContenidoPersonalizado);

}