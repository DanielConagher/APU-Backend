package com.APU_Backend.main.market.persistance.crud;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.APU_Backend.main.market.persistance.entity.Comentario;

public interface ComentarioCrudRepository extends CrudRepository<Comentario, Integer> {
    List<Comentario> findByContenidoIdContenido(int contenidoId);
}
