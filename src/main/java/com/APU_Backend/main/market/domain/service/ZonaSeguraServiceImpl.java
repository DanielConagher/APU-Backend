package com.APU_Backend.main.market.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.APU_Backend.main.market.domain.dto.ZonaSeguraDTO;
import com.APU_Backend.main.market.domain.repository.ZonaSeguraRepository;
import com.APU_Backend.main.market.persistance.mapper.ZonaSeguraMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ZonaSeguraServiceImpl
        implements ZonaSeguraService {

    private final ZonaSeguraRepository repository;

    private final ZonaSeguraMapper mapper;

    @Override
    public List<ZonaSeguraDTO> getAll() {

        return repository.getAll()
                .stream()
                .map(mapper::toDTO)
                .toList();

    }

}
