package com.APU_Backend.main.market.persistance.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estudiante_trofeo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteTrofeo {

    @EmbeddedId
    private EstudianteTrofeoId id;

    @Column(name = "fecha_obtencion")
    private LocalDateTime fechaObtencion;

    @ManyToOne
    @MapsId("idEstudiante")
    @JoinColumn(name = "id_estudiante")
    private Estudiante estudiante;

    @ManyToOne
    @MapsId("idTrofeo")
    @JoinColumn(name = "id_trofeo")
    private Trofeo trofeo;
}
