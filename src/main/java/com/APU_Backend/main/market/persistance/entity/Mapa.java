package com.APU_Backend.main.market.persistance.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "mapa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mapa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mapa")
    private Integer idMapa;

    @Column(name = "es_personalizado")
    private Boolean esPersonalizado;

    @ManyToOne
    @JoinColumn(name = "id_estudiante")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "id_tipo_desastre")
    private TipoDesastre tipoDesastre;

    @OneToMany(mappedBy = "mapa")
    @JsonIgnore
    private List<Nivel> niveles;

}
