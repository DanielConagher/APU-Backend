package com.APU_Backend.main.market.domain.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.APU_Backend.main.market.persistance.entity.Contenido;
import com.APU_Backend.main.market.persistance.entity.Comentario;
import com.APU_Backend.main.market.domain.repository.ContenidoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnalisisComentariosServiceImpl
        implements AnalisisComentariosService {

    private final ContenidoRepository contenidoRepository;

    private final ResumenIAService resumenIAService;

    // Obtenemos todos los comentarios de una burbuja(contenido) especifica mediante
    // su ID
    // Luego, aplica el formateador para obtener los nombres del estudiante y su
    // comentario
    // Luego, crea una carpeta llamada resumen, en caso no exista, con un archivo
    // llamado resumen_contenido_{id_contenido}.txt
    // Finalmente, escribe dentro del archivo el texto estatico junto con los
    // comentarios analizados simulando la respuesta de la IA.
    // En el futuro, al obtener todos los comentarios, se debe usar enviar a una ia
    // para que lo analice
    // Y, su respuesta se obtendra y guardara en una tabla de resumen de ia con la
    // que el admin podra ver
    // desde el page admin estos resumenes y una opcion para volver a generar un
    // resumen de una burbuja especifica
    // actualizando el ya existente.
    @Override
    public String analizarComentarios(Integer idContenido)
            throws Exception {

        Contenido contenido = contenidoRepository
                .getContenido(idContenido)
                .orElseThrow(() -> new RuntimeException(
                        "Contenido no encontrado"));

        if (contenido.getComentarios() == null
                || contenido.getComentarios().isEmpty()) {

            throw new RuntimeException(
                    "El contenido no tiene comentarios");
        }

        String comentariosTexto = contenido.getComentarios()
                .stream()
                .map(this::formatearComentario)
                .collect(Collectors.joining("\n"));

        String resumen = resumenIAService
                .generarResumen(comentariosTexto);

        guardarArchivo(idContenido, resumen);

        return resumen;
    }

    // Obtiene el nombre y apellido del estududiante y su descripcion(el comentario
    // escrito por el).
    private String formatearComentario(
            Comentario comentario) {

        return comentario.getEstudiante()
                .getPrimerNombre()
                + " "
                + comentario.getEstudiante()
                        .getPrimerApellido()
                + ": "
                + comentario.getDescripcion();
    }

    private void guardarArchivo(
            Integer idContenido,
            String resumen)
            throws Exception {

        Path carpeta = Paths.get("resumenes");

        if (!Files.exists(carpeta)) {

            Files.createDirectories(carpeta);
        }

        Path archivo = carpeta.resolve(
                "resumen_contenido_"
                        + idContenido
                        + ".txt");

        Files.writeString(archivo, resumen);
    }
}