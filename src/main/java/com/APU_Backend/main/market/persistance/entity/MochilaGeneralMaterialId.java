package com.APU_Backend.main.market.persistance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MochilaGeneralMaterialId implements Serializable {

    @Column(name = "id_mochila")
    private Integer idMochila;

    @Column(name = "id_material")
    private Integer idMaterial;
}
