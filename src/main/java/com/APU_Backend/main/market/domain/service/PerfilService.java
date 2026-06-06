package com.APU_Backend.main.market.domain.service;

import com.APU_Backend.main.market.domain.dto.*;

public interface PerfilService {

    PerfilDTO obtenerPerfil(
            Integer idEstudiante);

    PerfilDTO actualizarPerfil(
            Integer idEstudiante,
            ActualizarPerfilDTO request);
}