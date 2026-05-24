package com.APU_Backend.main.market.domain.service;

import org.springframework.stereotype.Service;

@Service
public class ResumenIAService {

    public String generarResumen(
            String comentarios) {

        return """
                RESUMEN GENERADO POR IA

                Comentarios analizados:

                """ +

                comentarios +

                """



                        CONCLUSIONES:

                        - Los estudiantes consideran útil el contenido.
                        - La teoría fue clara y fácil de entender.
                        - Algunos usuarios sugieren agregar más recursos visuales.
                        - El contenido genera interés y participación.
                        """;
    }
}
