package com.APU_Backend.main.market.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResumenComentariosDTO {

    private Integer idContenido;

    private Integer cantidadComentarios;

    private String resumen;

}
