package com.APU_Backend.main.market.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.APU_Backend.main.market.domain.dto.ProgresoAprendizajeDTO;
import com.APU_Backend.main.market.domain.repository.ProgresoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProgresoServiceImpl
        implements ProgresoService {

    private final ProgresoRepository progresoRepository;

    @Override
    public List<ProgresoAprendizajeDTO> obtenerProgresoAprendizaje(
            Integer idEstudiante) {

        return progresoRepository
                .obtenerProgresoAprendizaje(
                        idEstudiante);
    }
}