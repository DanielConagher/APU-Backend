package com.APU_Backend.main.market.domain.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.APU_Backend.main.market.domain.dto.DiscapacidadDTO;
import com.APU_Backend.main.market.persistance.crud.DiscapacidadCrudRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class DiscapacidadRepositoryImpl
        implements DiscapacidadRepository {

    private final DiscapacidadCrudRepository discapacidadRepo;

    @Override
    public List<DiscapacidadDTO> listar() {

        List<DiscapacidadDTO> lista = new ArrayList<>();

        discapacidadRepo.findAll().forEach(d -> {

            lista.add(

                    new DiscapacidadDTO(

                            d.getIdDiscapacidad(),

                            d.getNombre()

                    )

            );

        });

        return lista;

    }

}
