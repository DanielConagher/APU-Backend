package com.APU_Backend.main.market.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.APU_Backend.main.market.persistance.crud.ComentarioCrudRepository;
import com.APU_Backend.main.market.persistance.entity.Comentario;

@Repository
public class ComentarioRepositoryImpl implements ComentarioRepository {

    private final ComentarioCrudRepository comentarioCrudRepository;

    public ComentarioRepositoryImpl(ComentarioCrudRepository comentarioCrudRepository) {
        this.comentarioCrudRepository = comentarioCrudRepository;
    }

    @Override
    public List<Comentario> findByContenidoId(int contenidoId) {
        return comentarioCrudRepository.findByContenidoIdContenido(contenidoId);
    }

    @Override
    public void guardar(
            Comentario comentario) {

        comentarioCrudRepository
                .save(comentario);
    }
}