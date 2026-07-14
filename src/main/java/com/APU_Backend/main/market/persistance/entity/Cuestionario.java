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

    @Column(name = "experiencia_ganada")
    private Integer experienciaGanada;

    @Column(columnDefinition = "TEXT")
    private String retroalimentacion;

    private String tipo;

    @Column(name = "num_desastres")
    private Integer numDesastres;

    @OneToOne(mappedBy = "cuestionario")
    private Contenido contenido;

    @OneToMany(mappedBy = "cuestionario")
    @JsonIgnore
    private List<Pregunta> pregunta;

    @OneToMany(mappedBy = "cuestionario")
    @JsonIgnore
    private List<ResultadoCuestionario> resultados;

    @OneToOne(mappedBy = "cuestionario")
    @JsonIgnore
    private ContenidoPersonalizado contenidoPersonalizado;
}
