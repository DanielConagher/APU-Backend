package com.APU_Backend.main.market.domain.service;

import org.springframework.stereotype.Service;

import com.APU_Backend.main.market.domain.dto.CuestionarioDTO;
import com.APU_Backend.main.market.domain.dto.ResolverCuestionarioDTO;
import com.APU_Backend.main.market.domain.dto.ResultadoCuestionarioDTO;
import com.APU_Backend.main.market.domain.repository.CuestionarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CuestionarioServiceImpl
        implements CuestionarioService {

    private final CuestionarioRepository cuestionarioRepository;

    @Override
    public CuestionarioDTO obtenerPorContenido(
            Integer idContenido) {

        return cuestionarioRepository
                .obtenerPorContenido(idContenido);
    }

    @Override
    public ResultadoCuestionarioDTO resolverCuestionario(
            Integer idEstudiante,
            ResolverCuestionarioDTO request) {

        return cuestionarioRepository.resolverCuestionario(
                idEstudiante,
                request);
    }
}