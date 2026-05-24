package com.APU_Backend.main.market.domain.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContenidoDTO {

    private String teoria;

    private List<String> imagenes;

    private List<String> videos;

    private List<ComentarioDTO> comentarios;
}
