package com.APU_Backend.main.market.persistance.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "credenciales")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Credenciales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_credencial")
    private Integer idCredencial;

    private String correo;

    private String contrasena;

    @OneToOne
    @JoinColumn(name = "id_estudiante")
    private Estudiante estudiante;

    @OneToOne
    @JoinColumn(name = "id_administrador")
    private Administrador administrador;
}