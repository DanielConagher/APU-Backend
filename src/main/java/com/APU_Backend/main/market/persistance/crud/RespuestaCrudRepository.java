package com.APU_Backend.main.market.persistance.crud;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.APU_Backend.main.market.persistance.entity.Respuesta;

public interface RespuestaCrudRepository extends CrudRepository<Respuesta, Integer> {
    Optional<Respuesta> findByIdRespuestaAndPregunta_IdPregunta(
            Integer idRespuesta,
            Integer idPregunta);
}
