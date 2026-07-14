package com.APU_Backend.main.market.domain.service;

import org.springframework.stereotype.Service;

import com.APU_Backend.main.market.domain.dto.ActualizarMochilaGeneralDTO;
import com.APU_Backend.main.market.domain.dto.MochilaGeneralDTO;
import com.APU_Backend.main.market.domain.repository.MochilaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MochilaServiceImpl implements MochilaService {

    private final MochilaRepository mochilaRepository;

    @Override
    public MochilaGeneralDTO obtenerMochilaGeneral(Integer idEstudiante) {

        return mochilaRepository.obtenerMochilaGeneral(idEstudiante);

    }

    @Override
    public MochilaGeneralDTO guardarCambiosGeneral(
            Integer idEstudiante,
            ActualizarMochilaGeneralDTO request) {

        return mochilaRepository.guardarCambiosGeneral(
                idEstudiante,
                request);

    }

}