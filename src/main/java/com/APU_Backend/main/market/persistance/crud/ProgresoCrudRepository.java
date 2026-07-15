package com.APU_Backend.main.market.persistance.crud;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.APU_Backend.main.market.persistance.entity.Progreso;

public interface ProgresoCrudRepository
        extends CrudRepository<Progreso, Integer> {

    Optional<Progreso> findByEstudiante_IdEstudianteAndContenido_IdContenido(
            Integer idEstudiante,
            Integer idContenido);

}