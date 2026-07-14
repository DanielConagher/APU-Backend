package com.APU_Backend.main.market.persistance.crud;

import org.springframework.data.jpa.repository.JpaRepository;

import com.APU_Backend.main.market.persistance.entity.ContenidoPersonalizado;

public interface ContenidoPersonalizadoCrudRepository
        extends JpaRepository<ContenidoPersonalizado, Integer> {

}