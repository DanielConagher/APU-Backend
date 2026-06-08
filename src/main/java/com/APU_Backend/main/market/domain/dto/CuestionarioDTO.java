package com.APU_Backend.main.market.domain.dto;

import java.util.List;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CuestionarioDTO {

    private Integer idCuestionario;

    private Integer experienciaGanada;

    private String retroalimentacion;

    private String tipo;

    private Integer numDesastres;

    private List<PreguntaDTO> preguntas;
}