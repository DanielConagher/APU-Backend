package com.APU_Backend.main.market.domain.repository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.APU_Backend.main.market.domain.dto.AuthResponseDTO;
import com.APU_Backend.main.market.domain.dto.RegistroDTO;
import com.APU_Backend.main.market.domain.service.JwtService;
import com.APU_Backend.main.market.persistance.crud.CredencialesCrudRepository;
import com.APU_Backend.main.market.persistance.crud.EstudianteCrudRepository;
import com.APU_Backend.main.market.persistance.crud.UbicacionCrudRepository;
import com.APU_Backend.main.market.persistance.entity.Credenciales;
import com.APU_Backend.main.market.persistance.entity.Estudiante;
import com.APU_Backend.main.market.persistance.entity.Ubicacion;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CredencialesRepositoryImpl implements CredencialesRepository {

    private final EstudianteCrudRepository estudianteRepo;
    private final CredencialesCrudRepository credencialesRepo;
    private final UbicacionCrudRepository ubicacionRepo;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponseDTO registrar(RegistroDTO request) {

        if (credencialesRepo.existsByCorreo(
                request.getCorreo())) {

            throw new RuntimeException(
                    "El correo ya existe");
        }

        Ubicacion ubicacion = ubicacionRepo.findById(
                request.getIdUbicacion())
                .orElseThrow(() -> new RuntimeException(
                        "Ubicación no encontrada"));

        Estudiante estudiante = new Estudiante();

        estudiante.setPrimerNombre(
                request.getPrimerNombre());

        estudiante.setSegundoNombre(
                request.getSegundoNombre());

        estudiante.setPrimerApellido(
                request.getPrimerApellido());

        estudiante.setSegundoApellido(
                request.getSegundoApellido());

        estudiante.setEdad(
                request.getEdad());

        estudiante.setEsPadre(
                request.getEsPadre());

        estudiante.setUbicacion(
                ubicacion);

        estudiante = estudianteRepo.save(estudiante);

        Credenciales credenciales = new Credenciales();

        credenciales.setCorreo(
                request.getCorreo());

        credenciales.setContrasena(
                passwordEncoder.encode(
                        request.getContrasena()));

        credenciales.setEstudiante(
                estudiante);

        credencialesRepo.save(
                credenciales);

        // Este metodo crea el token con el id de estudiante y su correo, al final
        // devuelve un string con
        // el token creado
        String token = jwtService.generateToken(
                estudiante.getIdEstudiante(),
                request.getCorreo());

        // Vamos a devolver un authresponsedto que al llegar al frontend se recibira
        // como un objeto
        // json, ejm:
        // {
        // "idUsuario": 1,
        // "correo": "carlos@gmail.com",
        // "token": "eyJhbGciOiJIUzI1NiJ9..."
        // }
        return new AuthResponseDTO(estudiante.getIdEstudiante(), request.getCorreo(), token);
    }
}
