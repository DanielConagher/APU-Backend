package com.APU_Backend.main.market.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.APU_Backend.main.market.domain.dto.UbicacionDTO;
import com.APU_Backend.main.market.domain.repository.UbicacionRepository;
import com.APU_Backend.main.market.persistance.mapper.UbicacionMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UbicacionServiceImpl
        implements UbicacionService {

    private final UbicacionRepository ubicacionRepository;

    private final UbicacionMapper ubicacionMapper;

    @Override
    public List<UbicacionDTO> getAll() {

        return ubicacionRepository.getAll()
                .stream()
                .map(ubicacionMapper::toDTO)
                .toList();
    }
}