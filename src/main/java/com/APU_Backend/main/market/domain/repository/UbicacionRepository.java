package com.APU_Backend.main.market.domain.repository;

import java.util.List;

import com.APU_Backend.main.market.persistance.entity.Ubicacion;

public interface UbicacionRepository {

    List<Ubicacion> getAll();

}