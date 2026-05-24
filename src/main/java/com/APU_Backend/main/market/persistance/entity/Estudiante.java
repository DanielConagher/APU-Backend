package com.APU_Backend.main.market.persistance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "estudiante")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estudiante")
    private Integer idEstudiante;

    @Column(name = "primer_nombre")
    private String primerNombre;

    @Column(name = "segundo_nombre")
    private String segundoNombre;

    @Column(name = "primer_apellido")
    private String primerApellido;

    @Column(name = "segundo_apellido")
    private String segundoApellido;

    private Integer edad;

    @Column(name = "es_padre")
    private Boolean esPadre;

    @Column(name = "ultima_conexion")
    private LocalDateTime ultimaConexion;

    @ManyToOne
    @JoinColumn(name = "id_ubicacion")
    private Ubicacion ubicacion;

    @ManyToMany
    @JoinTable(name = "estudiante_discapacidad", joinColumns = @JoinColumn(name = "id_estudiante"), inverseJoinColumns = @JoinColumn(name = "id_discapacidad"))
    private List<Discapacidad> discapacidades;

    @OneToOne(mappedBy = "estudiante")
    private Credenciales credenciales;

    @OneToOne(mappedBy = "estudiante")
    private MochilaGeneral mochilaGeneral;

    @OneToMany(mappedBy = "estudiante")
    @JsonIgnore
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "estudiante")
    @JsonIgnore
    private List<Notificacion> notificaciones;

    @OneToMany(mappedBy = "estudiante")
    @JsonIgnore
    private List<Cuestionario> cuestionarios;

    @OneToMany(mappedBy = "estudiante")
    @JsonIgnore
    private List<Experiencia> experiencias;

    @OneToOne(mappedBy = "estudiante")
    @JsonIgnore
    private MochilaPersonalizada mochilaPersonalizada;

    @OneToMany(mappedBy = "estudiante")
    @JsonIgnore
    private List<Mapa> mapas;

    @OneToMany(mappedBy = "estudiante")
    @JsonIgnore
    private List<EstudianteInsignia> insignias;

    @OneToMany(mappedBy = "estudiante")
    @JsonIgnore
    private List<EstudianteTrofeo> trofeos;

    @OneToMany(mappedBy = "estudiante")
    @JsonIgnore
    private List<Progreso> progresos;

}
