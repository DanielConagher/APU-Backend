package com.APU_Backend.main.market.persistance.entity;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MochilaPersonalizadaMaterialId implements Serializable {

    @Column(name = "id_mochila")
    private Integer idMochila;

    @Column(name = "id_material")
    private Integer idMaterial;
}
