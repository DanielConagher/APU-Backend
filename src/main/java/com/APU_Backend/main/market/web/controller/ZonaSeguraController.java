package com.APU_Backend.main.market.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.APU_Backend.main.market.domain.dto.ZonaSeguraDTO;
import com.APU_Backend.main.market.domain.service.ZonaSeguraService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/zonas-seguras")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ZonaSeguraController {

    private final ZonaSeguraService zonaSeguraService;

    @GetMapping
    public List<ZonaSeguraDTO> getAll() {

        return zonaSeguraService.getAll();

    }

}
