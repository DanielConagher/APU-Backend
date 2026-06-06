package com.APU_Backend.main.market.persistance.crud;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.APU_Backend.main.market.persistance.entity.Pregunta;

public interface PreguntaCrudRepository extends CrudRepository<Pregunta, Integer> {
    Optional<Pregunta> findByIdPreguntaAndCuestionario_IdCuestionario(
            Integer idPregunta,
            Integer idCuestionario);
}
