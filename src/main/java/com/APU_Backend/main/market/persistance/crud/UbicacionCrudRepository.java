package com.APU_Backend.main.market.persistance.crud;

import com.APU_Backend.main.market.persistance.entity.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UbicacionCrudRepository
        extends JpaRepository<Ubicacion, Integer> {
}
