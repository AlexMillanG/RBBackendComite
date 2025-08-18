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
                             RoleRepository roleRepository
                             ) {  // Fixed parameter name
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {
        RoleEntity adminRole = roleRepository.findByName("ADMIN")
                .orElseGet(() -> {
                    RoleEntity role = new RoleEntity();
                    role.setName("ADMIN");
                    return roleRepository.save(role);
                });

        if (!userRepository.existsByUsername("admin")) {
            UserEntity admin = new UserEntity();
            admin.setUsername("admin");
            admin.setPassword(PasswordEncoderService.encodePassword("admin123"));  // Fixed method name
            admin.setFullName("Administrador");
            admin.setEmail("admin@admin.com");
            admin.setPhone("0000000000");
            admin.setRole(adminRole);

            userRepository.save(admin);

            System.out.println("Usuario admin creado correctamente!");
        }
    }
}