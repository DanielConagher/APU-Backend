package com.APU_Backend.main.market.domain.service;

import java.util.List;

import com.APU_Backend.main.market.domain.IA.GeminiService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class GeminiServiceImpl implements GeminiService {

    private final RestClient restClient;

    private final ObjectMapper objectMapper;

    @Value("${gemini.api.key}")
    private String apiKey;

    @Value("${gemini.model}")
    private String model;

    @Override
    public String generarResumen(String comentarios) {

        try {

            String prompt = """
                    Eres un asistente educativo.

                    Resume los siguientes comentarios de estudiantes.

                    No menciones nombres.

                    Agrupa ideas repetidas.

                    Divide el resumen en:

                    - Aspectos positivos.
                    - Aspectos negativos.
                    - Sugerencias.

                    Comentarios:

                    %s
                    """.formatted(comentarios);

            Map<String, Object> body = Map.of(

                    "contents",

                    new Object[] {

                            Map.of(

                                    "parts",

                                    new Object[] {

                                            Map.of(
                                                    "text",
                                                    prompt)

                                    })

                    });

            String respuesta = restClient.post()

                    .uri("https://generativelanguage.googleapis.com/v1beta/models/"
                            + model
                            + ":generateContent?key="
                            + apiKey)

                    .contentType(MediaType.APPLICATION_JSON)

                    .body(body)

                    .retrieve()

                    .body(String.class);

            JsonNode json = objectMapper.readTree(respuesta);

            if (json.has("error")) {

                throw new RuntimeException(

                        json.get("error")
                                .get("message")
                                .asText());

            }

            return json

                    .path("candidates")

                    .get(0)

                    .path("content")

                    .path("parts")

                    .get(0)

                    .path("text")

                    .asText();

        } catch (Exception e) {

            throw new RuntimeException(

                    "No fue posible generar el resumen: "
                            + e.getMessage());

        }

    }

}
