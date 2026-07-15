package com.APU_Backend.main.market.domain.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoCuestionarioDTO {

    private Integer nota;

    private Boolean aprobado;

    private Integer experienciaGanada;

    private String retroalimentacion;
}