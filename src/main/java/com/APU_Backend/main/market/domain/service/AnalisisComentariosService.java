package com.APU_Backend.main.market.domain.service;

import com.APU_Backend.main.market.domain.dto.ResumenComentariosDTO;

public interface AnalisisComentariosService {

    String analizarComentarios(Integer idContenido, String authHeader);

}
