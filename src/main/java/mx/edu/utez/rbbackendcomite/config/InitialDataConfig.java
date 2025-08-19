// InitialDataConfig.java
package mx.edu.utez.rbbackendcomite.config;

import jakarta.annotation.PostConstruct;
import mx.edu.utez.rbbackendcomite.models.role.RoleEntity;
import mx.edu.utez.rbbackendcomite.models.role.RoleRepository;
import mx.edu.utez.rbbackendcomite.models.user.UserEntity;
import mx.edu.utez.rbbackendcomite.models.user.UserRepository;
import mx.edu.utez.rbbackendcomite.utils.PasswordEncoderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitialDataConfig {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public InitialDataConfig(UserRepository userRepository,
            RoleRepository roleRepository) { // Fixed parameter name
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
        // Crear roles si no existen
        RoleEntity adminRole = roleRepository.findByName("ADMIN")
                .orElseGet(() -> {
                    RoleEntity role = new RoleEntity();
                    role.setName("ADMIN");
                    return roleRepository.save(role);
                });

        RoleEntity userRole = roleRepository.findByName("GROUP_ADMIN")
                .orElseGet(() -> {
                    RoleEntity role = new RoleEntity();
                    role.setName("GROUP_ADMIN");
                    return roleRepository.save(role);
                });

        RoleEntity managerRole = roleRepository.findByName("MEMBER")
                .orElseGet(() -> {
                    RoleEntity role = new RoleEntity();
                    role.setName("MEMBER");
                    return roleRepository.save(role);
                });

        // Usuario 1: Administrador
        if (!userRepository.existsByUsername("admin")) {
            UserEntity admin = new UserEntity();
            admin.setUsername("admin");
            admin.setPassword(PasswordEncoderService.encodePassword("AdminSecure123!"));
            admin.setFullName("Administrador Principal");
            admin.setEmail("admin@correo.com");
            admin.setPhone("5512345678");
            admin.setRole(adminRole);
            userRepository.save(admin);
            System.out.println("Usuario admin creado correctamente!");
        }

        // Usuario 2: Usuario normal
        if (!userRepository.existsByUsername("usuario1")) {
            UserEntity normalUser = new UserEntity();
            normalUser.setUsername("usuario1");
            normalUser.setPassword(PasswordEncoderService.encodePassword("UserPass123!"));
            normalUser.setFullName("Juan Pérez");
            normalUser.setEmail("juan.perez@correo.com");
            normalUser.setPhone("5511223344");
            normalUser.setRole(userRole);
            userRepository.save(normalUser);
            System.out.println("Usuario normal creado correctamente!");
        }

        // Usuario 3: Gerente/Manager
        if (!userRepository.existsByUsername("gerente1")) {
            UserEntity manager = new UserEntity();
            manager.setUsername("gerente1");
            manager.setPassword(PasswordEncoderService.encodePassword("ManagerPass456!"));
            manager.setFullName("María García");
            manager.setEmail("maria.garcia@correo.com");
            manager.setPhone("5599887766");
            manager.setRole(managerRole);
            userRepository.save(manager);
            System.out.println("Usuario gerente creado correctamente!");
        }

        // Usuario 4: Otro usuario con rol diferente (opcional)
        if (!userRepository.existsByUsername("invitado")) {
            UserEntity guest = new UserEntity();
            guest.setUsername("invitado");
            guest.setPassword(PasswordEncoderService.encodePassword("GuestPass789!"));
            guest.setFullName("Invitado Especial");
            guest.setEmail("invitado@correo.com");
            guest.setPhone("5533445566");
            guest.setRole(userRole); // O podrías crear un rol GUEST si lo necesitas
            userRepository.save(guest);
            System.out.println("Usuario invitado creado correctamente!");
        }
    }
}