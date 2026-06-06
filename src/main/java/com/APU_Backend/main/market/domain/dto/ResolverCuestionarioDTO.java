package com.APU_Backend.main.market.domain.dto;

import java.util.List;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResolverCuestionarioDTO {

    private Integer idCuestionario;

    private Integer idContenido;

    private List<RespuestaUsuarioDTO> respuestas;
}