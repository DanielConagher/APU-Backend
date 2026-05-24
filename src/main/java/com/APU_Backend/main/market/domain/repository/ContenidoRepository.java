package com.APU_Backend.main.market.domain.repository;

import java.util.Optional;

import com.APU_Backend.main.market.persistance.entity.Contenido;

public interface ContenidoRepository {

    Optional<Contenido> getContenido(int contenidoId);

}
