package com.APU_Backend.main.market.domain.repository;

import com.APU_Backend.main.market.domain.dto.*;

public interface PerfilRepository {

    PerfilDTO obtenerPerfil(
            Integer idEstudiante);

    PerfilDTO actualizarPerfil(
            Integer idEstudiante,
            ActualizarPerfilDTO request);
}