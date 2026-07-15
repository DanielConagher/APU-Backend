package com.APU_Backend.main.market.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.APU_Backend.main.market.persistance.crud.ZonaSeguraCrudRepository;
import com.APU_Backend.main.market.persistance.entity.ZonaSegura;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ZonaSeguraRepositoryImpl
        implements ZonaSeguraRepository {

    private final ZonaSeguraCrudRepository crud;

    @Override
    public List<ZonaSegura> getAll() {

        return crud.findAll();

    }

}