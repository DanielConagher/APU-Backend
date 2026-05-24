package com.APU_Backend.main.market.persistance.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "discapacidad")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Discapacidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_discapacidad")
    private Integer idDiscapacidad;

    private String nombre;

    @ManyToMany(mappedBy = "discapacidades")
    @JsonIgnore
    private List<Estudiante> estudiantes;
}
