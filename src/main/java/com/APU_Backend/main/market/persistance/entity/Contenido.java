package com.APU_Backend.main.market.persistance.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "contenido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contenido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contenido")
    private Integer idContenido;

    @Column(columnDefinition = "TEXT")
    private String teoria;

    @Column(columnDefinition = "json")
    private String imagenes;

    @Column(columnDefinition = "json")
    private String videos;

    @Column(name = "experiencia_ganada")
    private Integer experienciaGanada;

    @Column(name = "es_cuestionario")
    private Boolean esCuestionario;

    @Column(name = "num_desastres")
    private Integer numDesastres;

    @ManyToOne
    @JoinColumn(name = "id_nivel")
    private Nivel nivel;

    @OneToOne(mappedBy = "contenido")
    private Progreso progreso;

    @OneToOne
    @JoinColumn(name = "id_cuestionario")
    private Cuestionario cuestionario;

    @OneToMany(mappedBy = "contenido")
    @JsonIgnore
    private List<Comentario> comentarios;

}
