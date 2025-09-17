package main.service;

import main.entity.Usuario;
import main.entity.Rol;
import main.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        // Convertir roles a GrantedAuthority (sin prefijo ROLE_)
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Rol rol : usuario.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(rol.getNombre())); // SOLO el nombre
        }

        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getPassword())
                .authorities(authorities) // Usamos authorities en vez de roles
                .build();
    }
}
