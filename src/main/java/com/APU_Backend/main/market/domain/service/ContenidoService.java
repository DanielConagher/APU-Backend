package com.APU_Backend.main.market.domain.service;

import org.springframework.stereotype.Service;

import com.APU_Backend.main.market.domain.dto.ContenidoDTO;
import com.APU_Backend.main.market.domain.repository.ContenidoRepository;
import com.APU_Backend.main.market.persistance.entity.Contenido;
import com.APU_Backend.main.market.persistance.mapper.ContenidoMapper;

@Service
public class ContenidoService {

    private final ContenidoRepository contenidoRepository;

    private final ContenidoMapper contenidoMapper;

    public ContenidoService(
            ContenidoRepository contenidoRepository,
            ContenidoMapper contenidoMapper) {
        this.contenidoRepository = contenidoRepository;
        this.contenidoMapper = contenidoMapper;
    }

    public ContenidoDTO obtenerContenido(
            int id) {

        Contenido contenido = contenidoRepository.getContenido(id)
                .orElseThrow(() -> new RuntimeException(
                        "Contenido no encontrado"));

        return contenidoMapper.toContenidoDTO(
                contenido);
    }
}
