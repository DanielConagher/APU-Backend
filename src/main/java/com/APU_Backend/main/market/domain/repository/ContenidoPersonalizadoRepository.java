package com.APU_Backend.main.market.domain.repository;

import java.util.Optional;

import com.APU_Backend.main.market.persistance.entity.ContenidoPersonalizado;

public interface ContenidoPersonalizadoRepository {

    Optional<ContenidoPersonalizado> getContenido(
            Integer idContenidoPersonalizado);

}