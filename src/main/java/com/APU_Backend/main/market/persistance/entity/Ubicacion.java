package com.APU_Backend.main.market.persistance.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ubicacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ubicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ubicacion")
    private Integer idUbicacion;

    private String nombre;

    @OneToMany(mappedBy = "ubicacion")
    @JsonIgnore
    private List<Estudiante> estudiantes;

    @OneToMany(mappedBy = "ubicacion")
    @JsonIgnore
    private List<ZonaSegura> zonasSeguras;

}
