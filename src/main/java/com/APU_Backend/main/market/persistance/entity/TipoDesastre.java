package com.APU_Backend.main.market.persistance.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tipo_desastre")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoDesastre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_desastre")
    private Integer idTipoDesastre;

    private String nombre;

    @OneToMany(mappedBy = "tipoDesastre")
    @JsonIgnore
    private List<Mapa> mapas;
}
