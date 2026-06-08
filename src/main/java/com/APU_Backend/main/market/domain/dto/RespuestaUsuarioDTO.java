package com.APU_Backend.main.market.domain.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RespuestaUsuarioDTO {

    private Integer idPregunta;

    private Integer idRespuestaSeleccionada;
}