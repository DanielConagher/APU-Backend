package com.APU_Backend.main.market.persistance.crud;

import org.springframework.data.repository.CrudRepository;

import com.APU_Backend.main.market.persistance.entity.ResultadoCuestionario;

public interface ResultadoCuestionarioCrudRepository
        extends CrudRepository<ResultadoCuestionario, Integer> {

    boolean existsByEstudiante_IdEstudianteAndCuestionario_IdCuestionario(
            Integer idEstudiante,
            Integer idCuestionario);

}