package com.APU_Backend.main.market.domain.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActualizarMaterialPersonalizadoDTO {

    private String nombre;

    private Integer cantidad;

    private Boolean conseguido;

}