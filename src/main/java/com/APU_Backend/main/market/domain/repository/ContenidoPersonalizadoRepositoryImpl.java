package com.APU_Backend.main.market.domain.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.APU_Backend.main.market.persistance.crud.ContenidoPersonalizadoCrudRepository;
import com.APU_Backend.main.market.persistance.entity.ContenidoPersonalizado;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ContenidoPersonalizadoRepositoryImpl
        implements ContenidoPersonalizadoRepository {

    private final ContenidoPersonalizadoCrudRepository crud;

    @Override
    public Optional<ContenidoPersonalizado> getContenido(
            Integer idContenidoPersonalizado) {

        return crud.findById(idContenidoPersonalizado);

    }

}