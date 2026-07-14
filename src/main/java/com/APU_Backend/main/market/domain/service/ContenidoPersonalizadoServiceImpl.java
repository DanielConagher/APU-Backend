package com.APU_Backend.main.market.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.APU_Backend.main.market.domain.dto.ContenidoPersonalizadoDTO;
import com.APU_Backend.main.market.domain.repository.ContenidoPersonalizadoRepository;
import com.APU_Backend.main.market.persistance.entity.ContenidoPersonalizado;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContenidoPersonalizadoServiceImpl
        implements ContenidoPersonalizadoService {

    private final ContenidoPersonalizadoRepository repository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ContenidoPersonalizadoDTO obtenerContenido(
            Integer idContenidoPersonalizado) {

        ContenidoPersonalizado contenido = repository.getContenido(
                idContenidoPersonalizado)

                .orElseThrow(
                        () -> new RuntimeException(
                                "Contenido personalizado no encontrado"));

        return convertirDTO(contenido);

    }

    private ContenidoPersonalizadoDTO convertirDTO(
            ContenidoPersonalizado contenido) {

        return new ContenidoPersonalizadoDTO(

                contenido.getTeoria(),

                convertirJson(contenido.getImagenes()),

                convertirJson(contenido.getVideos()),

                contenido.getEsCuestionario()

        );

    }

    private List<String> convertirJson(
            String json) {

        if (json == null) {
            return List.of();
        }

        try {

            return objectMapper.readValue(
                    json,
                    new TypeReference<List<String>>() {
                    });

        } catch (Exception e) {

            throw new RuntimeException(
                    "Error convirtiendo JSON");

        }

    }

}