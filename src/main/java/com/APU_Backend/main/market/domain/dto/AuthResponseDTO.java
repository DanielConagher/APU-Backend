package com.APU_Backend.main.market.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthResponseDTO {

    private Integer idUsuario;
    private String correo;
    private String token;
    private String rol;
}