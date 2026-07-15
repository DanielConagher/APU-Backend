package com.APU_Backend.main.market.domain.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MapaAprendizajeDTO {

    private Integer numeroNivel;

    private Integer posicion;

    private Integer idContenido;

    private Integer estado;

    private String titulo;

    private Boolean esCuestionario;

}