package com.APU_Backend.main.market.persistance.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cuestionario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cuestionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuestionario")
    private Integer idCuestionario;

    private Boolean completada;

    @Column(name = "experiencia_ganada")
    private Integer experienciaGanada;

    @Column(columnDefinition = "TEXT")
    private String retroalimentacion;

    private String tipo;

    @Column(name = "num_desastres")
    private Integer numDesastres;

    @ManyToOne
    @JoinColumn(name = "id_estudiante")
    private Estudiante estudiante;

    @OneToOne(mappedBy = "cuestionario")
    private Contenido contenido;

    @OneToOne(mappedBy = "cuestionario")
    private Notificacion notificacion;

    @OneToMany(mappedBy = "cuestionario")
    @JsonIgnore
    private List<Pregunta> pregunta;

}
