package com.APU_Backend.main.market.domain.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.APU_Backend.main.market.persistance.crud.EstudianteCrudRepository;
import com.APU_Backend.main.market.persistance.entity.Estudiante;

@Repository
public class EstudianteRepositoryImpl
        implements EstudianteRepository {

    private final EstudianteCrudRepository estudianteCrudRepository;

    public EstudianteRepositoryImpl(

            EstudianteCrudRepository estudianteCrudRepository) {

        this.estudianteCrudRepository = estudianteCrudRepository;
    }

    @Override
    public Optional<Estudiante> findById(
            Integer id) {

        return estudianteCrudRepository
                .findById(id);
    }
}