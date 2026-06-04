package com.APU_Backend.main.market.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.APU_Backend.main.market.domain.dto.UbicacionDTO;
import com.APU_Backend.main.market.domain.service.UbicacionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/ubicaciones")
@RequiredArgsConstructor
public class UbicacionController {

    private final UbicacionService ubicacionService;

    @GetMapping
    public List<UbicacionDTO> getAll() {

        return ubicacionService.getAll();
    }
}
