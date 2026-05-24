package com.APU_Backend.main.market.persistance.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "respuesta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_respuesta")
    private Integer idRespuesta;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private Boolean esCorrecta;

    @ManyToOne
    @JoinColumn(name = "id_pregunta")
    private Pregunta pregunta;
}
