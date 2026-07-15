package com.APU_Backend.main.market.domain.repository;

import com.APU_Backend.main.market.domain.dto.ActualizarMaterialPersonalizadoDTO;
import com.APU_Backend.main.market.domain.dto.CrearMaterialPersonalizadoDTO;
import com.APU_Backend.main.market.domain.dto.MochilaPersonalizadaDTO;

public interface MochilaPersonalizadaRepository {

    MochilaPersonalizadaDTO obtenerMochilaPersonalizada(
            Integer idEstudiante);

    MochilaPersonalizadaDTO agregarMaterial(
            Integer idEstudiante,
            CrearMaterialPersonalizadoDTO request);

    MochilaPersonalizadaDTO actualizarMaterial(
            Integer idMaterialPersonalizado,
            ActualizarMaterialPersonalizadoDTO request);

    MochilaPersonalizadaDTO eliminarMaterial(
            Integer idMaterialPersonalizado);

}