package com.APU_Backend.main.market.persistance.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "trofeo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Trofeo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_trofeo")
    private Integer idTrofeo;

    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "experiencia_ganada")
    private Integer experienciaGanada;

    @OneToMany(mappedBy = "trofeo")
    @JsonIgnore
    private List<EstudianteTrofeo> estudiantes;
}
