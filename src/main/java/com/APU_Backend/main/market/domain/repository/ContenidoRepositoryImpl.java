package com.APU_Backend.main.market.domain.repository;

import org.springframework.stereotype.Repository;

import com.APU_Backend.main.market.domain.dto.ContenidoAdminDTO;
import com.APU_Backend.main.market.persistance.crud.ContenidoCrudRepository;
import com.APU_Backend.main.market.persistance.entity.Contenido;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Repository
public class ContenidoRepositoryImpl
        implements ContenidoRepository {

    private final ContenidoCrudRepository contenidoCrudRepository;

    public ContenidoRepositoryImpl(
            ContenidoCrudRepository contenidoCrudRepository) {
        this.contenidoCrudRepository = contenidoCrudRepository;
    }

    @Override
    public Optional<Contenido> getContenido(int contenidoId) {

        return contenidoCrudRepository.findById(contenidoId);
    }

    @Override
    public List<ContenidoAdminDTO> listarContenidos() {

        return StreamSupport.stream(
                contenidoCrudRepository.findAll().spliterator(),
                false)

                .map(c -> new ContenidoAdminDTO(

                        c.getIdContenido(),

                        c.getTitulo(),

                        c.getNivel().getNumeroNivel()

                ))

                .sorted(
                        Comparator.comparing(ContenidoAdminDTO::getNivel)
                                .thenComparing(ContenidoAdminDTO::getIdContenido))

                .toList();
    }
}
