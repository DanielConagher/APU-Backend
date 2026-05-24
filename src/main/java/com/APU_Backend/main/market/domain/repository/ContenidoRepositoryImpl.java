package com.APU_Backend.main.market.domain.repository;

import org.springframework.stereotype.Repository;

import com.APU_Backend.main.market.persistance.crud.ContenidoCrudRepository;
import com.APU_Backend.main.market.persistance.entity.Contenido;

import java.util.Optional;

@Repository
public class ContenidoRepositoryImpl
        implements ContenidoRepository {

    private final ContenidoCrudRepository contenidoCrudRepository;

    public ContenidoRepositoryImpl(
            ContenidoCrudRepository contenidoCrudRepository) {
        this.contenidoCrudRepository = contenidoCrudRepository;
    }

    @Override
    public Optional<Contenido> getContenido(int contenidoId) {

        return contenidoCrudRepository.findById(contenidoId);
    }
}
