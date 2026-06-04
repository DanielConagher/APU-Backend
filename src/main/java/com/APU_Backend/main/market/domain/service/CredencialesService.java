package com.APU_Backend.main.market.domain.service;

import com.APU_Backend.main.market.domain.dto.AuthResponseDTO;
import com.APU_Backend.main.market.domain.dto.LoginDTO;
import com.APU_Backend.main.market.domain.dto.RegistroDTO;

public interface CredencialesService {
    AuthResponseDTO registrar(RegistroDTO request);

    AuthResponseDTO login(LoginDTO request);
}
