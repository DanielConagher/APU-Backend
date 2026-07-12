package com.APU_Backend.main.market.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.APU_Backend.main.market.domain.dto.DiscapacidadDTO;
import com.APU_Backend.main.market.domain.repository.DiscapacidadRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiscapacidadServiceImpl
        implements DiscapacidadService {

    private final DiscapacidadRepository repository;

    @Override
    public List<DiscapacidadDTO> listar() {

        return repository.listar();

    }

}