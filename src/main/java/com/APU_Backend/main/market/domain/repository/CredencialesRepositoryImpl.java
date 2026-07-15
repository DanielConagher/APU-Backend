package com.APU_Backend.main.market.domain.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.APU_Backend.main.market.domain.dto.AuthResponseDTO;
import com.APU_Backend.main.market.domain.dto.LoginDTO;
import com.APU_Backend.main.market.domain.dto.RegistroDTO;
import com.APU_Backend.main.market.domain.service.JwtService;
import com.APU_Backend.main.market.persistance.crud.CredencialesCrudRepository;
import com.APU_Backend.main.market.persistance.crud.DiscapacidadCrudRepository;
import com.APU_Backend.main.market.persistance.crud.EstudianteCrudRepository;
import com.APU_Backend.main.market.persistance.crud.UbicacionCrudRepository;
import com.APU_Backend.main.market.persistance.entity.Credenciales;
import com.APU_Backend.main.market.persistance.entity.Discapacidad;
import com.APU_Backend.main.market.persistance.entity.Estudiante;
import com.APU_Backend.main.market.persistance.entity.Ubicacion;
import com.APU_Backend.main.market.util.PasswordValidator;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class CredencialesRepositoryImpl implements CredencialesRepository {

        private final EstudianteCrudRepository estudianteRepo;
        private final CredencialesCrudRepository credencialesRepo;
        private final UbicacionCrudRepository ubicacionRepo;

        private final DiscapacidadCrudRepository discapacidadRepo;

        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;

        @Transactional
        @Override
        public AuthResponseDTO registrar(RegistroDTO request) {

                if (!PasswordValidator.esPasswordValida(
                                request.getContrasena())) {

                        throw new RuntimeException(
                                        "La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula, un número y un carácter especial");
                }

                if (credencialesRepo.existsByCorreo(
                                request.getCorreo())) {

                        throw new RuntimeException(
                                        "El correo ya existe");
                }

                Ubicacion ubicacion = ubicacionRepo.findById(
                                request.getIdUbicacion())
                                .orElseThrow(() -> new RuntimeException(
                                                "Ubicación no encontrada"));

                // Esto asegura que las discapacidades que se envien en el registro realmente
                // existan en la bd.
                List<Discapacidad> discapacidades = (List<Discapacidad>) discapacidadRepo.findAllById(
                                request.getDiscapacidades());

                if (discapacidades.size() != request.getDiscapacidades().size()) {

                        throw new RuntimeException(
                                        "Una o más discapacidades no existen");
                }

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

                estudiante.setDiscapacidades(
                                discapacidades);

                estudiante = estudianteRepo.save(
                                estudiante);

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

                String rol = "ESTUDIANTE";

                String token = jwtService.generateToken(
                                estudiante.getIdEstudiante(),
                                request.getCorreo(),
                                rol);

                String nombreCompleto = estudiante.getPrimerNombre() + " "
                                + estudiante.getPrimerApellido();

                return new AuthResponseDTO(
                                estudiante.getIdEstudiante(),
                                request.getCorreo(),
                                token,
                                rol,
                                nombreCompleto);
        }

        @Override
        public AuthResponseDTO login(LoginDTO request) {

                Credenciales credenciales = credencialesRepo
                                .findByCorreo(request.getCorreo())
                                .orElseThrow(() -> new RuntimeException(
                                                "Correo no encontrado"));

                boolean passwordValida = passwordEncoder.matches(
                                request.getContrasena(),
                                credenciales.getContrasena());

                if (!passwordValida) {

                        throw new RuntimeException(
                                        "Contraseña incorrecta");
                }

                String rol;

                Integer idUsuario;

                String nombreCompleto;

                if (credenciales.getAdministrador() != null) {

                        rol = "ADMINISTRADOR";

                        idUsuario = credenciales
                                        .getAdministrador()
                                        .getIdAdministrador();

                        nombreCompleto = credenciales
                                        .getAdministrador()
                                        .getPrimerNombre()
                                        + " "
                                        +
                                        credenciales
                                                        .getAdministrador()
                                                        .getPrimerApellido();

                } else {

                        rol = "ESTUDIANTE";

                        idUsuario = credenciales
                                        .getEstudiante()
                                        .getIdEstudiante();

                        nombreCompleto = credenciales
                                        .getEstudiante()
                                        .getPrimerNombre()
                                        + " "
                                        +
                                        credenciales
                                                        .getEstudiante()
                                                        .getPrimerApellido();

                }

                String token = jwtService.generateToken(
                                idUsuario,
                                credenciales.getCorreo(),
                                rol);

                return new AuthResponseDTO(
                                idUsuario,
                                credenciales.getCorreo(),
                                token,
                                rol,
                                nombreCompleto);
        }
}
