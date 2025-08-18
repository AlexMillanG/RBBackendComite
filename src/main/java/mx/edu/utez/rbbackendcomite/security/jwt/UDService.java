package mx.edu.utez.rbbackendcomite.security.jwt;

import mx.edu.utez.rbbackendcomite.models.user.UserEntity;
import mx.edu.utez.rbbackendcomite.models.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

// TERCER PASO: Crear nuestro servicio de gestión de autoridades
@Service
public class UDService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscar primero al usuario
        UserEntity found = userRepository.findByUsername(username).orElse(null);
        if(found == null) throw new UsernameNotFoundException("Usuario no encontrado");

        // Generar las autoridades para el contexto de seguridad
        // authority = ROLE_ADMIN, ROLE_EMPLOYEE -> filterChain
        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + found.getRole().getName().toUpperCase());

        // Retornar el objeto de usuario para registrar en el contexto de seguridad
        return new User(
                found.getEmail(),
                found.getPassword(),
                Collections.singleton(authority)
        );
    }
}
