package com.APU_Backend.main.market.domain.repository;

import java.util.Optional;

import com.APU_Backend.main.market.persistance.entity.Estudiante;

public interface EstudianteRepository {

    Optional<Estudiante> findById(Integer id);
}