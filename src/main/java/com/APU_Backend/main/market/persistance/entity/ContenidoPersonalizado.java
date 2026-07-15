package com.APU_Backend.main.market.persistance.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "contenido_personalizado")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContenidoPersonalizado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contenido_personalizado")
    private Integer idContenidoPersonalizado;

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

    private String titulo;

    private Integer posicion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nivel")
    private Nivel nivel;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cuestionario")
    private Cuestionario cuestionario;

    @ManyToMany
    @JoinTable(name = "contenido_personalizado_discapacidad", joinColumns = @JoinColumn(name = "id_contenido_personalizado"), inverseJoinColumns = @JoinColumn(name = "id_discapacidad"))
    private List<Discapacidad> discapacidades;

    @OneToMany(mappedBy = "contenidoPersonalizado")
    @JsonIgnore
    private List<ProgresoPersonalizado> progresos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_desastre")
    private TipoDesastre tipoDesastre;
}