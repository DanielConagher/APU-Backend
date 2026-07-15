package com.APU_Backend.main.market.persistance.mapper;

import org.springframework.stereotype.Component;

import com.APU_Backend.main.market.domain.dto.ZonaSeguraDTO;
import com.APU_Backend.main.market.persistance.entity.ZonaSegura;

@Component
public class ZonaSeguraMapper {

    public ZonaSeguraDTO toDTO(ZonaSegura zona) {

        return new ZonaSeguraDTO(

                zona.getIdZona(),

                zona.getNombre(),

                zona.getDescripcion(),

                zona.getUbicacion().getIdUbicacion(),

                zona.getUbicacion().getNombre()

        );

    }

}
