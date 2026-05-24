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

    @EmbeddedId
    private MochilaPersonalizadaMaterialId id;

    private Integer cantidad;

    private Boolean conseguido;

    @ManyToOne
    @MapsId("idMochila")
    @JoinColumn(name = "id_mochila")
    private MochilaPersonalizada mochilaPersonalizada;

    @ManyToOne
    @MapsId("idMaterial")
    @JoinColumn(name = "id_material")
    private MaterialMochila materialMochila;
}
