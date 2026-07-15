package com.APU_Backend.main.market.domain.dto;

import java.util.List;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActualizarMochilaGeneralDTO {

    private List<ActualizarMaterialDTO> materiales;

}