package com.APU_Backend.main.market.persistance.crud;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.APU_Backend.main.market.persistance.entity.MochilaPersonalizada;

public interface MochilaPersonalizadaCrudRepository
        extends CrudRepository<MochilaPersonalizada, Integer> {

    Optional<MochilaPersonalizada> findByEstudiante_IdEstudiante(Integer idEstudiante);

}