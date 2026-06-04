package com.APU_Backend.main.market.domain.service;

import org.springframework.stereotype.Service;

import com.APU_Backend.main.market.domain.dto.AuthResponseDTO;
import com.APU_Backend.main.market.domain.dto.LoginDTO;
import com.APU_Backend.main.market.domain.dto.RegistroDTO;
import com.APU_Backend.main.market.domain.repository.CredencialesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CredencialesServiceImpl
        implements CredencialesService {

    private final CredencialesRepository credencialesRepository;

    @Override
    public AuthResponseDTO registrar(RegistroDTO request) {

        return credencialesRepository.registrar(request);

    }

    @Override
    public AuthResponseDTO login(LoginDTO request) {
        return credencialesRepository.login(request);
    }
}