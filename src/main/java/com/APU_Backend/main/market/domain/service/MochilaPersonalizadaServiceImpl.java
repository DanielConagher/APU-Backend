package com.APU_Backend.main.market.domain.service;

import org.springframework.stereotype.Service;

import com.APU_Backend.main.market.domain.dto.ActualizarMaterialPersonalizadoDTO;
import com.APU_Backend.main.market.domain.dto.CrearMaterialPersonalizadoDTO;
import com.APU_Backend.main.market.domain.dto.MochilaPersonalizadaDTO;
import com.APU_Backend.main.market.domain.repository.MochilaPersonalizadaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MochilaPersonalizadaServiceImpl
        implements MochilaPersonalizadaService {

    private final MochilaPersonalizadaRepository mochilaPersonalizadaRepository;

    @Override
    public MochilaPersonalizadaDTO obtenerMochilaPersonalizada(
            Integer idEstudiante) {

        return mochilaPersonalizadaRepository
                .obtenerMochilaPersonalizada(idEstudiante);

    }

    @Override
    public MochilaPersonalizadaDTO agregarMaterial(
            Integer idEstudiante,
            CrearMaterialPersonalizadoDTO request) {

        return mochilaPersonalizadaRepository
                .agregarMaterial(
                        idEstudiante,
                        request);

    }

    @Override
    public MochilaPersonalizadaDTO actualizarMaterial(
            Integer idMaterialPersonalizado,
            ActualizarMaterialPersonalizadoDTO request) {

        return mochilaPersonalizadaRepository
                .actualizarMaterial(
                        idMaterialPersonalizado,
                        request);

    }

    @Override
    public MochilaPersonalizadaDTO eliminarMaterial(
            Integer idMaterialPersonalizado) {

        return mochilaPersonalizadaRepository
                .eliminarMaterial(
                        idMaterialPersonalizado);

    }

}