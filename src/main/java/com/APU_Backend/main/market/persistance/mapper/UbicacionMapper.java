package com.APU_Backend.main.market.persistance.mapper;

import org.springframework.stereotype.Component;

import com.APU_Backend.main.market.domain.dto.UbicacionDTO;
import com.APU_Backend.main.market.persistance.entity.Ubicacion;

@Component
public class UbicacionMapper {

    public UbicacionDTO toDTO(Ubicacion ubicacion) {

        return new UbicacionDTO(
                ubicacion.getIdUbicacion(),
                ubicacion.getNombre());
    }
}
