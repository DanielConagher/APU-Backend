package com.APU_Backend.main.market.domain.service;

import org.springframework.stereotype.Service;

import com.APU_Backend.main.market.domain.repository.ProgresoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProgresoServiceImpl implements ProgresoService {

    private final ProgresoRepository progresoRepository;

    @Override
    public void completarContenido(
            Integer idEstudiante,
            Integer idContenido) {

        progresoRepository.completarContenido(
                idEstudiante,
                idContenido);
    }
}
