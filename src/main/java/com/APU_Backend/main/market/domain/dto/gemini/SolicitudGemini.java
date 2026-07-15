package com.APU_Backend.main.market.domain.dto.gemini;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudGemini {

    private List<ContenidoGemini> contents;

    private GenerationConfig generationConfig;

}
