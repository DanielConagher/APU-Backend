package com.APU_Backend.main.market.domain.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.APU_Backend.main.market.domain.dto.*;
import com.APU_Backend.main.market.persistance.crud.*;
import com.APU_Backend.main.market.persistance.entity.*;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PerfilRepositoryImpl
                implements PerfilRepository {

        private final EstudianteCrudRepository estudianteRepo;

        private final CredencialesCrudRepository credencialesRepo;

        private final UbicacionCrudRepository ubicacionRepo;

        private final DiscapacidadCrudRepository discapacidadRepo;

        @Override
        public PerfilDTO obtenerPerfil(
                        Integer idEstudiante) {

                Estudiante estudiante = estudianteRepo.findById(
                                idEstudiante)
                                .orElseThrow(() -> new RuntimeException(
                                                "Estudiante no encontrado"));

                Credenciales credenciales = estudiante.getCredenciales();

                List<Integer> idsDiscapacidades =

                                estudiante.getDiscapacidades()

                                                .stream()

                                                .map(Discapacidad::getIdDiscapacidad)

                                                .toList();

                return new PerfilDTO(

                                estudiante.getIdEstudiante(),

                                estudiante.getPrimerNombre(),

                                estudiante.getSegundoNombre(),

                                estudiante.getPrimerApellido(),

                                estudiante.getSegundoApellido(),

                                credenciales.getCorreo(),

                                estudiante.getUbicacion().getIdUbicacion(),

                                idsDiscapacidades

                );
        }

        @Override
        public PerfilDTO actualizarPerfil(
                        Integer idEstudiante,
                        ActualizarPerfilDTO request) {

                Estudiante estudiante = estudianteRepo.findById(
                                idEstudiante)
                                .orElseThrow(() -> new RuntimeException(
                                                "Estudiante no encontrado"));

                Credenciales credenciales = estudiante.getCredenciales();

                if (!credenciales.getCorreo()
                                .equalsIgnoreCase(request.getCorreo())
                                &&
                                credencialesRepo.existsByCorreo(
                                                request.getCorreo())) {

                        throw new RuntimeException(
                                        "El correo ya existe");
                }

                Ubicacion ubicacion = ubicacionRepo.findById(
                                request.getIdUbicacion())
                                .orElseThrow(() -> new RuntimeException(
                                                "Ubicación no encontrada"));

                estudiante.setPrimerNombre(
                                request.getPrimerNombre());

                estudiante.setSegundoNombre(
                                request.getSegundoNombre());

                estudiante.setPrimerApellido(
                                request.getPrimerApellido());

                estudiante.setSegundoApellido(
                                request.getSegundoApellido());

                estudiante.setUbicacion(
                                ubicacion);

                Iterable<Discapacidad> iterable =

                                discapacidadRepo.findAllById(
                                                request.getIdsDiscapacidades());

                List<Discapacidad> discapacidades = new ArrayList<>();

                iterable.forEach(discapacidades::add);

                estudiante.setDiscapacidades(discapacidades);

                credenciales.setCorreo(
                                request.getCorreo());

                estudianteRepo.save(
                                estudiante);

                credencialesRepo.save(
                                credenciales);

                return obtenerPerfil(
                                idEstudiante);
        }
}