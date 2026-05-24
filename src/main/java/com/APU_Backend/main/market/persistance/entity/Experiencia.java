package com.APU_Backend.main.market.persistance.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "experiencia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Experiencia {

    @Id
    @Column(name = "id_experiencia")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idExperiencia;

    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "id_estudiante")
    private Estudiante estudiante;
}
