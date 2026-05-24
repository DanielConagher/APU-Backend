package com.APU_Backend.main.market.persistance.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notificacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificacion")
    private Integer idNotificacion;

    private String tipo;

    @Column(columnDefinition = "TEXT")
    private String contenido;

    private String imagen;

    @ManyToOne
    @JoinColumn(name = "id_estudiante")
    private Estudiante estudiante;

    @OneToOne
    @JoinColumn(name = "id_cuestionario")
    private Cuestionario cuestionario;
}