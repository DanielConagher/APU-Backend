package com.APU_Backend.main.market.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "El formato del correo es incorrecto")
    private String correo;

    @NotBlank(message = "La contrasena es obligatoria")
    private String contrasena;
}