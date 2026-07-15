package com.APU_Backend.main.market.domain.repository;

import java.util.List;
import java.util.Optional;

import com.APU_Backend.main.market.domain.dto.ContenidoAdminDTO;
import com.APU_Backend.main.market.persistance.entity.Contenido;

public interface ContenidoRepository {

    Optional<Contenido> getContenido(int contenidoId);

    List<ContenidoAdminDTO> listarContenidos();

}
