package com.APU_Backend.main.market.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.APU_Backend.main.market.domain.dto.DiscapacidadDTO;
import com.APU_Backend.main.market.domain.service.DiscapacidadService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/discapacidades")
@RequiredArgsConstructor
public class DiscapacidadController {

    private final DiscapacidadService discapacidadService;

    @GetMapping
    public List<DiscapacidadDTO> listar() {

        return discapacidadService.listar();

    }

}
