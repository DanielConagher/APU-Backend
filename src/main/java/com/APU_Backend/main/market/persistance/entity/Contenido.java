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

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "posicion")
    private Integer posicion;

    @OneToMany(mappedBy = "contenido", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Progreso> progresos;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cuestionario")
    private Cuestionario cuestionario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nivel")
    private Nivel nivel;

    @OneToMany(mappedBy = "contenido", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Comentario> comentarios;

    @ManyToMany
    @JoinTable(name = "contenido_discapacidad", joinColumns = @JoinColumn(name = "id_contenido"), inverseJoinColumns = @JoinColumn(name = "id_discapacidad"))
    private List<Discapacidad> discapacidades;

}
