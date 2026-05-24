package com.APU_Backend.main.market.persistance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "log")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_log")
    private Integer idLog;

    private String accion;

    @Column(name = "fecha_accion")
    private LocalDateTime fechaAccion;

    @ManyToOne
    @JoinColumn(name = "id_administrador")
    private Administrador administrador;

}
