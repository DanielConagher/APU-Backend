package com.APU_Backend.main.market.domain.service;

import org.springframework.stereotype.Service;

import com.APU_Backend.main.market.domain.dto.*;
import com.APU_Backend.main.market.domain.repository.PerfilRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PerfilServiceImpl
        implements PerfilService {

    private final PerfilRepository repository;

    @Override
    public PerfilDTO obtenerPerfil(
            Integer idEstudiante) {

        return repository.obtenerPerfil(
                idEstudiante);
    }

    @Override
    public PerfilDTO actualizarPerfil(
            Integer idEstudiante,
            ActualizarPerfilDTO request) {

        return repository.actualizarPerfil(
                idEstudiante,
                request);
    }
}