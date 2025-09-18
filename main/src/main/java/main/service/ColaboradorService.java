package main.service;

import main.entity.Colaborador;
import main.entity.Usuario;
import main.repository.ColaboradorRepository;
import main.repository.RolRepository;
import main.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ColaboradorService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final ColaboradorRepository colaboradorRepository;

    @Autowired
    public ColaboradorService(UsuarioRepository usuarioRepository,
                          RolRepository rolRepository,
                          PasswordEncoder passwordEncoder, ColaboradorRepository colaboradorRepository) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
        this.colaboradorRepository = colaboradorRepository;
    }

    public Colaborador registrarColaborador(Colaborador Colaborador) {
        Usuario usuario = Colaborador.getUsuario();

        // Encriptar contraseña
        if (usuario.getPassword() == null || usuario.getPassword().isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        // Asignar rol ROLE_Colaborador
        rolRepository.findById(2L).ifPresent(rol -> usuario.setRoles(Set.of(rol)));


        // Guardar usuario primero
        usuarioRepository.save(usuario);

        // Guardar Colaborador
        return colaboradorRepository.save(Colaborador);
    }

    public List<Colaborador> findAll() {
        return colaboradorRepository.findAll();
    }

    public Optional<Colaborador> obtenerPorUsuario(Usuario usuario) {
        return colaboradorRepository.findByUsuario(usuario);
    }


}
