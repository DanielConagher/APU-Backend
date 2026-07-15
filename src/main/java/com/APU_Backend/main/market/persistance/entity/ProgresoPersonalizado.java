package com.APU_Backend.main.market.persistance.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "progreso_personalizado")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProgresoPersonalizado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_progreso_personalizado")
    private Integer idProgresoPersonalizado;

    private Integer completada;

    @ManyToOne
    @JoinColumn(name = "id_estudiante")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "id_contenido_personalizado")
    private ContenidoPersonalizado contenidoPersonalizado;

}