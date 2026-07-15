package com.APU_Backend.main.market.persistance.crud;

import org.springframework.data.jpa.repository.JpaRepository;

import com.APU_Backend.main.market.persistance.entity.ZonaSegura;

public interface ZonaSeguraCrudRepository
        extends JpaRepository<ZonaSegura, Integer> {

}