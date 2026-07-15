package com.APU_Backend.main.market.domain.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaterialPersonalizadoDTO {

    private Integer idMaterialPersonalizado;

    private String nombre;

    private Integer cantidad;

    private Boolean conseguido;

}