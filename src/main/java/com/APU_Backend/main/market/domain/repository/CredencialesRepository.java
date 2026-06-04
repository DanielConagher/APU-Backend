package com.APU_Backend.main.market.domain.repository;

import com.APU_Backend.main.market.domain.dto.AuthResponseDTO;
import com.APU_Backend.main.market.domain.dto.LoginDTO;
import com.APU_Backend.main.market.domain.dto.RegistroDTO;

public interface CredencialesRepository {
    public AuthResponseDTO registrar(RegistroDTO request);

    AuthResponseDTO login(LoginDTO request);
}
