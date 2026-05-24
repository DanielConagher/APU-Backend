package com.APU_Backend.main.market.persistance.entity;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class EstudianteInsigniaId implements Serializable {

    @Column(name = "id_estudiante")
    private Integer idEstudiante;

    @Column(name = "id_insignia")
    private Integer idInsignia;
}
