package com.APU_Backend.main.market.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.APU_Backend.main.market.persistance.crud.UbicacionCrudRepository;
import com.APU_Backend.main.market.persistance.entity.Ubicacion;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UbicacionRepositoryImpl
        implements UbicacionRepository {

    private final UbicacionCrudRepository ubicacionCrudRepository;

    @Override
    public List<Ubicacion> getAll() {

        return (List<Ubicacion>) ubicacionCrudRepository.findAll();
    }
}