package com.APU_Backend.main.market.persistance.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "nivel")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Nivel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nivel")
    private Integer idNivel;

    @Column(name = "numero_nivel")
    private Integer numeroNivel;

    @ManyToOne
    @JoinColumn(name = "id_mapa")
    private Mapa mapa;

    @OneToMany(mappedBy = "nivel")
    @JsonIgnore
    private List<Contenido> contenidos;
}
