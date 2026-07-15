package com.APU_Backend.main.market.persistance.crud;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.APU_Backend.main.market.persistance.entity.MochilaGeneralMaterial;
import com.APU_Backend.main.market.persistance.entity.MochilaGeneralMaterialId;

public interface MochilaGeneralMaterialCrudRepository
        extends CrudRepository<MochilaGeneralMaterial, MochilaGeneralMaterialId> {

    List<MochilaGeneralMaterial> findByMochilaGeneral_IdMochila(Integer idMochila);

}