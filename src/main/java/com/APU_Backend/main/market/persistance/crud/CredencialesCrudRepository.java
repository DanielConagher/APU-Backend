package com.APU_Backend.main.market.persistance.crud;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.APU_Backend.main.market.persistance.entity.Credenciales;

public interface CredencialesCrudRepository extends CrudRepository<Credenciales, Integer> {

    boolean existsByCorreo(String correo);

    Optional<Credenciales> findByCorreo(String correo);
}
