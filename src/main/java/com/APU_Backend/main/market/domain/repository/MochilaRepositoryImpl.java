package com.APU_Backend.main.market.domain.repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.APU_Backend.main.market.domain.dto.ActualizarMaterialDTO;
import com.APU_Backend.main.market.domain.dto.ActualizarMochilaGeneralDTO;
import com.APU_Backend.main.market.domain.dto.MaterialMochilaDTO;
import com.APU_Backend.main.market.domain.dto.MochilaGeneralDTO;
import com.APU_Backend.main.market.domain.repository.MochilaRepository;
import com.APU_Backend.main.market.persistance.crud.EstudianteCrudRepository;
import com.APU_Backend.main.market.persistance.crud.MaterialMochilaCrudRepository;
import com.APU_Backend.main.market.persistance.crud.MochilaGeneralCrudRepository;
import com.APU_Backend.main.market.persistance.crud.MochilaGeneralMaterialCrudRepository;
import com.APU_Backend.main.market.persistance.entity.Estudiante;
import com.APU_Backend.main.market.persistance.entity.MaterialMochila;
import com.APU_Backend.main.market.persistance.entity.MochilaGeneral;
import com.APU_Backend.main.market.persistance.entity.MochilaGeneralMaterial;
import com.APU_Backend.main.market.persistance.entity.MochilaGeneralMaterialId;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MochilaRepositoryImpl implements MochilaRepository {

    private final MochilaGeneralCrudRepository mochilaGeneralCrudRepository;

    private final MochilaGeneralMaterialCrudRepository mochilaGeneralMaterialCrudRepository;

    private final MaterialMochilaCrudRepository materialMochilaCrudRepository;

    private final EstudianteCrudRepository estudianteCrudRepository;

    @Override
    public MochilaGeneralDTO obtenerMochilaGeneral(Integer idEstudiante) {

        MochilaGeneral mochila = mochilaGeneralCrudRepository
                .findByEstudiante_IdEstudiante(idEstudiante)
                .orElseGet(() -> crearMochila(idEstudiante));

        return convertirDTO(mochila);
    }

    private MochilaGeneral crearMochila(Integer idEstudiante) {

        Estudiante estudiante = estudianteCrudRepository
                .findById(idEstudiante)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        MochilaGeneral mochila = new MochilaGeneral();

        mochila.setEstudiante(estudiante);

        mochila.setPorcentajeCompletado(
                BigDecimal.ZERO);

        mochila = mochilaGeneralCrudRepository.save(mochila);

        estudiante.setMochilaGeneral(mochila);

        Iterable<MaterialMochila> materiales = materialMochilaCrudRepository.findAll();

        for (MaterialMochila material : materiales) {

            MochilaGeneralMaterialId id = new MochilaGeneralMaterialId(
                    mochila.getIdMochila(),
                    material.getIdMaterial());

            MochilaGeneralMaterial mochilaMaterial = new MochilaGeneralMaterial();

            mochilaMaterial.setId(id);

            mochilaMaterial.setMochilaGeneral(mochila);

            mochilaMaterial.setMaterialMochila(material);

            mochilaMaterial.setCantidad(1);

            mochilaMaterial.setConseguido(false);

            mochilaGeneralMaterialCrudRepository
                    .save(mochilaMaterial);

        }

        return mochila;

    }

    @Override
    public MochilaGeneralDTO guardarCambiosGeneral(
            Integer idEstudiante,
            ActualizarMochilaGeneralDTO request) {

        MochilaGeneral mochila = mochilaGeneralCrudRepository
                .findByEstudiante_IdEstudiante(idEstudiante)
                .orElseThrow(() -> new RuntimeException(
                        "Mochila no encontrada"));

        for (ActualizarMaterialDTO materialDTO : request.getMateriales()) {

            MochilaGeneralMaterialId id = new MochilaGeneralMaterialId(
                    mochila.getIdMochila(),
                    materialDTO.getIdMaterial());

            MochilaGeneralMaterial material = mochilaGeneralMaterialCrudRepository
                    .findById(id)
                    .orElseThrow(() -> new RuntimeException(
                            "Material no encontrado"));

            material.setConseguido(
                    materialDTO.getConseguido());

            mochilaGeneralMaterialCrudRepository
                    .save(material);

        }

        actualizarPorcentaje(mochila);

        return convertirDTO(mochila);

    }

    private void actualizarPorcentaje(
            MochilaGeneral mochila) {

        List<MochilaGeneralMaterial> materiales = mochilaGeneralMaterialCrudRepository
                .findByMochilaGeneral_IdMochila(
                        mochila.getIdMochila());

        long completados = materiales.stream()
                .filter(
                        material -> Boolean.TRUE.equals(
                                material.getConseguido()))
                .count();

        BigDecimal porcentaje;

        if (materiales.isEmpty()) {

            porcentaje = BigDecimal.ZERO;

        } else {

            porcentaje = BigDecimal.valueOf(completados)
                    .multiply(BigDecimal.valueOf(100))
                    .divide(
                            BigDecimal.valueOf(materiales.size()),
                            2,
                            RoundingMode.HALF_UP);

        }

        mochila.setPorcentajeCompletado(
                porcentaje);

        mochilaGeneralCrudRepository
                .save(mochila);

    }

    private MochilaGeneralDTO convertirDTO(
            MochilaGeneral mochila) {

        List<MochilaGeneralMaterial> materialesEntidad = mochilaGeneralMaterialCrudRepository
                .findByMochilaGeneral_IdMochila(
                        mochila.getIdMochila());

        List<MaterialMochilaDTO> materialesDTO = new ArrayList<>();

        for (MochilaGeneralMaterial material : materialesEntidad) {

            MaterialMochilaDTO dto = new MaterialMochilaDTO();

            dto.setIdMaterial(
                    material.getMaterialMochila()
                            .getIdMaterial());

            dto.setNombre(
                    material.getMaterialMochila()
                            .getNombre());

            dto.setCantidad(
                    material.getCantidad());

            dto.setConseguido(
                    material.getConseguido());

            materialesDTO.add(dto);

        }

        return new MochilaGeneralDTO(
                mochila.getPorcentajeCompletado(),
                materialesDTO);

    }

}
