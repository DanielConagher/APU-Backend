package com.APU_Backend.main.market.domain.dto;

import java.util.List;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PerfilDTO {

    private Integer idEstudiante;

    private String primerNombre;

    private String segundoNombre;

    private String primerApellido;

    private String segundoApellido;

    private String correo;

    private Integer idUbicacion;

    private List<Integer> idsDiscapacidades;
}