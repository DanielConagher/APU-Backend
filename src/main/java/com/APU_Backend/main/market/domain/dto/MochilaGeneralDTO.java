package com.APU_Backend.main.market.domain.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MochilaGeneralDTO {

    private BigDecimal porcentajeCompletado;

    private List<MaterialMochilaDTO> materiales;

}