package main.service;

import main.entity.Cliente;
import main.entity.Rol;
import main.entity.Usuario;
import main.repository.ClienteRepository;
import main.repository.RolRepository;
import main.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Collections;
import java.util.Set;

@Service
public class ClienteService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente registrarCliente(Cliente cliente) {
        Usuario usuario = cliente.getUsuario();

        // Encriptar contraseña
        if (usuario.getPassword() == null || usuario.getPassword().isEmpty()) {
            throw new IllegalArgumentException("La contraseña no puede estar vacía");
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        // Asignar rol ROLE_CLIENTE
        rolRepository.findById(1L).ifPresent(rol -> usuario.setRoles(Set.of(rol)));


        // Guardar usuario primero
        usuarioRepository.save(usuario);

        // Guardar cliente
        return clienteRepository.save(cliente);
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }
}
