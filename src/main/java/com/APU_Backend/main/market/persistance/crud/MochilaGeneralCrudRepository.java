package com.APU_Backend.main.market.persistance.crud;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.APU_Backend.main.market.persistance.entity.MochilaGeneral;

public interface MochilaGeneralCrudRepository
        extends CrudRepository<MochilaGeneral, Integer> {

    Optional<MochilaGeneral> findByEstudiante_IdEstudiante(Integer idEstudiante);

}