package com.APU_Backend.main.market.domain.dto;

import lombok.*;

import jakarta.validation.constraints.Email;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistroDTO {
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private Boolean esPadre;
    private Integer edad;
    private Integer idUbicacion;

    @Email
    private String correo;
    private String contrasena;
}
