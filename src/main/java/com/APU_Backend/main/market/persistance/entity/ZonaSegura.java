package com.APU_Backend.main.market.persistance.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "zona_segura")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ZonaSegura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zona")
    private Integer idZona;

    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_ubicacion")
    private Ubicacion ubicacion;
}
