package com.APU_Backend.main.market.persistance.mapper;

import com.APU_Backend.main.market.domain.dto.ComentarioDTO;
import com.APU_Backend.main.market.domain.dto.ContenidoDTO;
import com.APU_Backend.main.market.persistance.entity.Comentario;
import com.APU_Backend.main.market.persistance.entity.Contenido;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContenidoMapper {

        private final ObjectMapper objectMapper;

        public ContenidoMapper(
                        ObjectMapper objectMapper) {
                this.objectMapper = objectMapper;
        }

        public ContenidoDTO toContenidoDTO(
                        Contenido contenido) {

                try {

                        List<String> imagenes = objectMapper.readValue(
                                        contenido.getImagenes(),
                                        new TypeReference<List<String>>() {
                                        });

                        List<String> videos = objectMapper.readValue(
                                        contenido.getVideos(),
                                        new TypeReference<List<String>>() {
                                        });

                        List<ComentarioDTO> comentarios = contenido.getComentarios()
                                        .stream()
                                        .map(this::toComentarioDTO)
                                        .toList();

                        return new ContenidoDTO(
                                        contenido.getTeoria(),
                                        imagenes,
                                        videos,
                                        comentarios);

                } catch (Exception e) {

                        throw new RuntimeException(
                                        "Error convirtiendo JSON");
                }
        }

        private ComentarioDTO toComentarioDTO(
                        Comentario comentario) {

                String nombreCompleto = comentario.getEstudiante().getPrimerNombre()
                                + " "
                                + comentario.getEstudiante().getPrimerApellido();

                return new ComentarioDTO(
                                comentario.getDescripcion(),
                                nombreCompleto);
        }
}