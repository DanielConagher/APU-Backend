package com.APU_Backend.main.market.persistance.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "material_mochila")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaterialMochila {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_material")
    private Integer idMaterial;

    private String nombre;

    @OneToMany(mappedBy = "materialMochila")
    @JsonIgnore
    private List<MochilaGeneralMaterial> mochilas;

    @OneToMany(mappedBy = "materialMochila")
    @JsonIgnore
    private List<MochilaPersonalizadaMaterial> mochilasPersonalizadas;
}
