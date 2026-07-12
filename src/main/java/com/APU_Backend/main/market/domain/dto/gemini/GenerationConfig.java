package com.APU_Backend.main.market.domain.dto.gemini;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenerationConfig {

    private Double temperature;

    private Integer maxOutputTokens;

}
