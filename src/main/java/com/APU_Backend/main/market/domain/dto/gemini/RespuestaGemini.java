package com.APU_Backend.main.market.domain.dto.gemini;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RespuestaGemini {

    private List<Candidato> candidates;

}