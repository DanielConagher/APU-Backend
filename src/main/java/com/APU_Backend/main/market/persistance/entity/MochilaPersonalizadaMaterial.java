package com.APU_Backend.main.market.persistance.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mochila_personalizada_material")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MochilaPersonalizadaMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_material_personalizado")
    private Integer idMaterialPersonalizado;

    private String nombre;

    private Integer cantidad;

    private Boolean conseguido;

    @ManyToOne
    @JoinColumn(name = "id_mochila")
    private MochilaPersonalizada mochilaPersonalizada;

}
