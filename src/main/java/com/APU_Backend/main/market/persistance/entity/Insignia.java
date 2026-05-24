package com.APU_Backend.main.market.persistance.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "insignia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Insignia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_insignia")
    private Integer idInsignia;

    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "experiencia_ganada")
    private Integer experienciaGanada;

    @OneToMany(mappedBy = "insignia")
    @JsonIgnore
    private List<EstudianteInsignia> estudiantes;
}
