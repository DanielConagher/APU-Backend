package com.APU_Backend.main.market.persistance.entity;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mochila_personalizada")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MochilaPersonalizada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mochila")
    private Integer idMochila;

    @Column(name = "porcentaje_completado")
    private BigDecimal porcentajeCompletado;

    @OneToOne
    @JoinColumn(name = "id_estudiante")
    private Estudiante estudiante;

    @OneToMany(mappedBy = "mochilaPersonalizada")
    @JsonIgnore
    private List<MochilaPersonalizadaMaterial> materiales;
}
