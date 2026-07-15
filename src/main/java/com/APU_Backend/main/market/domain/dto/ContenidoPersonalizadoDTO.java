package com.APU_Backend.main.market.domain.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContenidoPersonalizadoDTO {

    private String teoria;

    private List<String> imagenes;

    private List<String> videos;

    private Boolean esCuestionario;

}