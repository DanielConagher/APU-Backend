package com.APU_Backend.main.market.persistance.crud;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.APU_Backend.main.market.persistance.entity.MochilaPersonalizadaMaterial;

public interface MochilaPersonalizadaMaterialCrudRepository
        extends CrudRepository<MochilaPersonalizadaMaterial, Integer> {

    List<MochilaPersonalizadaMaterial> findByMochilaPersonalizada_IdMochila(Integer idMochila);

}