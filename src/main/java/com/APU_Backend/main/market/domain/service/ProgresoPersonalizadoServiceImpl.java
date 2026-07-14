package com.APU_Backend.main.market.domain.service;

import org.springframework.stereotype.Service;

import com.APU_Backend.main.market.domain.repository.ProgresoPersonalizadoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProgresoPersonalizadoServiceImpl
        implements ProgresoPersonalizadoService {

    private final ProgresoPersonalizadoRepository repository;

    @Override
    public void completarContenido(
            Integer idEstudiante,
            Integer idContenidoPersonalizado) {

        repository.completarContenido(
                idEstudiante,
                idContenidoPersonalizado);

    }

}
