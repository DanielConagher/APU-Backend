package com.APU_Backend.main.market.domain.repository;

import java.util.List;
import com.APU_Backend.main.market.persistance.entity.Comentario;

public interface ComentarioRepository {

    List<Comentario> findByContenidoId(int contenidoId);
}