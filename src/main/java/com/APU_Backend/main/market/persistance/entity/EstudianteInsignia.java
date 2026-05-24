package com.APU_Backend.main.market.persistance.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estudiante_insignia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstudianteInsignia {

    @EmbeddedId
    private EstudianteInsigniaId id;

    @Column(name = "fecha_obtencion")
    private LocalDateTime fechaObtencion;

    @ManyToOne
    @MapsId("idEstudiante")
    @JoinColumn(name = "id_estudiante")
    private Estudiante estudiante;

    @ManyToOne
    @MapsId("idInsignia")
    @JoinColumn(name = "id_insignia")
    private Insignia insignia;
}
