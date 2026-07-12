package com.APU_Backend.main.market.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.APU_Backend.main.market.domain.dto.MapaAprendizajeDTO;
import com.APU_Backend.main.market.domain.repository.MapaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MapaServiceImpl
                implements MapaService {

        private final MapaRepository mapaRepository;

        @Override
        public List<MapaAprendizajeDTO> obtenerMapaAprendizaje(
                        Integer idEstudiante,
                        Integer idTipoDesastre) {

                return mapaRepository
                                .obtenerMapaAprendizaje(
                                                idEstudiante,
                                                idTipoDesastre);
        }

        @Override
        public List<MapaAprendizajeDTO> obtenerMapaPersonalizado(
                        Integer idEstudiante,
                        Integer idTipoDesastre) {

                return mapaRepository.obtenerMapaPersonalizado(
                                idEstudiante,
                                idTipoDesastre);
        }
}