package com.APU_Backend.main.market.domain.repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.APU_Backend.main.market.domain.dto.ActualizarMaterialPersonalizadoDTO;
import com.APU_Backend.main.market.domain.dto.CrearMaterialPersonalizadoDTO;
import com.APU_Backend.main.market.domain.dto.MaterialPersonalizadoDTO;
import com.APU_Backend.main.market.domain.dto.MochilaPersonalizadaDTO;

import com.APU_Backend.main.market.persistance.crud.EstudianteCrudRepository;
import com.APU_Backend.main.market.persistance.crud.MochilaPersonalizadaCrudRepository;
import com.APU_Backend.main.market.persistance.crud.MochilaPersonalizadaMaterialCrudRepository;

import com.APU_Backend.main.market.persistance.entity.Estudiante;
import com.APU_Backend.main.market.persistance.entity.MochilaPersonalizada;
import com.APU_Backend.main.market.persistance.entity.MochilaPersonalizadaMaterial;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MochilaPersonalizadaRepositoryImpl
        implements MochilaPersonalizadaRepository {

    private final MochilaPersonalizadaCrudRepository mochilaPersonalizadaCrudRepository;

    private final MochilaPersonalizadaMaterialCrudRepository materialCrudRepository;

    private final EstudianteCrudRepository estudianteCrudRepository;

    @Override
    public MochilaPersonalizadaDTO obtenerMochilaPersonalizada(
            Integer idEstudiante) {

        MochilaPersonalizada mochila = mochilaPersonalizadaCrudRepository
                .findByEstudiante_IdEstudiante(idEstudiante)
                .orElseGet(
                        () -> crearMochila(idEstudiante));

        return convertirDTO(mochila);

    }

    private MochilaPersonalizada crearMochila(
            Integer idEstudiante) {

        Estudiante estudiante = estudianteCrudRepository
                .findById(idEstudiante)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Estudiante no encontrado"));

        MochilaPersonalizada mochila = new MochilaPersonalizada();

        mochila.setEstudiante(estudiante);

        mochila.setPorcentajeCompletado(
                BigDecimal.ZERO);

        return mochilaPersonalizadaCrudRepository
                .save(mochila);

    }

    @Override
    public MochilaPersonalizadaDTO agregarMaterial(
            Integer idEstudiante,
            CrearMaterialPersonalizadoDTO request) {

        MochilaPersonalizada mochila = mochilaPersonalizadaCrudRepository
                .findByEstudiante_IdEstudiante(idEstudiante)
                .orElseGet(
                        () -> crearMochila(idEstudiante));

        MochilaPersonalizadaMaterial material = new MochilaPersonalizadaMaterial();

        material.setNombre(
                request.getNombre());

        material.setCantidad(
                request.getCantidad());

        material.setConseguido(false);

        material.setMochilaPersonalizada(
                mochila);

        materialCrudRepository.save(material);

        actualizarPorcentaje(mochila);

        return convertirDTO(mochila);

    }

    @Override
    public MochilaPersonalizadaDTO actualizarMaterial(
            Integer idMaterialPersonalizado,
            ActualizarMaterialPersonalizadoDTO request) {

        MochilaPersonalizadaMaterial material = materialCrudRepository
                .findById(idMaterialPersonalizado)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Material no encontrado"));

        material.setNombre(
                request.getNombre());

        material.setCantidad(
                request.getCantidad());

        material.setConseguido(
                request.getConseguido());

        materialCrudRepository.save(material);

        MochilaPersonalizada mochila = material.getMochilaPersonalizada();

        actualizarPorcentaje(mochila);

        return convertirDTO(mochila);

    }

    @Override
    public MochilaPersonalizadaDTO eliminarMaterial(
            Integer idMaterialPersonalizado) {

        MochilaPersonalizadaMaterial material = materialCrudRepository
                .findById(idMaterialPersonalizado)
                .orElseThrow(
                        () -> new RuntimeException(
                                "Material no encontrado"));

        MochilaPersonalizada mochila = material.getMochilaPersonalizada();

        materialCrudRepository.delete(material);

        actualizarPorcentaje(mochila);

        return convertirDTO(mochila);

    }

    private void actualizarPorcentaje(
            MochilaPersonalizada mochila) {

        List<MochilaPersonalizadaMaterial> materiales = materialCrudRepository
                .findByMochilaPersonalizada_IdMochila(
                        mochila.getIdMochila());

        long completados = materiales.stream()
                .filter(
                        m -> Boolean.TRUE.equals(
                                m.getConseguido()))
                .count();

        BigDecimal porcentaje;

        if (materiales.isEmpty()) {

            porcentaje = BigDecimal.ZERO;

        } else {

            porcentaje = BigDecimal.valueOf(completados)
                    .multiply(
                            BigDecimal.valueOf(100))
                    .divide(
                            BigDecimal.valueOf(
                                    materiales.size()),
                            2,
                            RoundingMode.HALF_UP);

        }

        mochila.setPorcentajeCompletado(
                porcentaje);

        mochilaPersonalizadaCrudRepository.save(
                mochila);

    }

    private MochilaPersonalizadaDTO convertirDTO(
            MochilaPersonalizada mochila) {

        List<MochilaPersonalizadaMaterial> materiales = materialCrudRepository
                .findByMochilaPersonalizada_IdMochila(
                        mochila.getIdMochila());

        List<MaterialPersonalizadoDTO> lista = new ArrayList<>();

        for (MochilaPersonalizadaMaterial material : materiales) {

            lista.add(
                    new MaterialPersonalizadoDTO(
                            material.getIdMaterialPersonalizado(),
                            material.getNombre(),
                            material.getCantidad(),
                            material.getConseguido()));

        }

        return new MochilaPersonalizadaDTO(
                mochila.getPorcentajeCompletado(),
                lista);

    }

}