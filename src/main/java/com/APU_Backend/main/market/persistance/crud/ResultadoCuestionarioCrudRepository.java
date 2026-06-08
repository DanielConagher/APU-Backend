package com.APU_Backend.main.market.persistance.crud;

import org.springframework.data.repository.CrudRepository;

import com.APU_Backend.main.market.persistance.entity.ResultadoCuestionario;

public interface ResultadoCuestionarioCrudRepository
        extends CrudRepository<ResultadoCuestionario, Integer> {

    /*
     * Esta funcion verificara que, antes de ingresar un nuevo registro a la tabla resultado_cuestionario de un
     * estudiante ha completado un cuestionario, primero verificara si el estudiante ya ha completado este cuestionario
     * antes. Esto para no crear muchos registros con el mismo id estudiante y el mismo id cuestionario.
     */
    boolean existsByEstudiante_IdEstudianteAndCuestionario_IdCuestionario(
            Integer idEstudiante,
            Integer idCuestionario);

}