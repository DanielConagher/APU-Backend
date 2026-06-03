package com.APU_Backend.main.market.persistance.crud;

import org.springframework.data.repository.CrudRepository;

import com.APU_Backend.main.market.persistance.entity.Credenciales;

public interface CredencialesCrudRepository extends CrudRepository<Credenciales, Integer> {

    boolean existsByCorreo(String correo);
}
