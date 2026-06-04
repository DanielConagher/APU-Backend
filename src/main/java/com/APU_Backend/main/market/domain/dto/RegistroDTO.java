package com.APU_Backend.main.market.domain.dto;

import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistroDTO {

    @NotBlank(message = "El primer nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El primer nombre debe tener entre 2 y 50 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "El primer nombre solo puede contener letras")
    private String primerNombre;

    @Size(max = 50, message = "El segundo nombre no puede superar los 50 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]*$", message = "El segundo nombre solo puede contener letras")
    private String segundoNombre;

    @NotBlank(message = "El primer apellido es obligatorio")
    @Size(min = 2, max = 50, message = "El primer apellido debe tener entre 2 y 50 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "El primer apellido solo puede contener letras")
    private String primerApellido;

    @Size(max = 50, message = "El segundo apellido no puede superar los 50 caracteres")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]*$", message = "El segundo apellido solo puede contener letras")
    private String segundoApellido;

    @NotNull(message = "Debe indicar si es padre o madre")
    private Boolean esPadre;

    @NotNull(message = "La edad es obligatoria")
    @Min(value = 5, message = "La edad mínima permitida es 5 años")
    @Max(value = 120, message = "La edad máxima permitida es 120 años")
    private Integer edad;

    @NotNull(message = "Debe seleccionar una ubicación")
    private Integer idUbicacion;

    @Email(message = "El formato del correo es incorrecto")
    @NotBlank(message = "El correo es obligatorio")
    @Size(max = 100, message = "El correo no puede superar los 100 caracteres")
    private String correo;

    @NotBlank(message = "La contraseña es obligatoria")
    private String contrasena;

    @NotNull(message = "La lista de discapacidades no puede ser nula")
    private List<Integer> discapacidades;
}