package mx.edu.utez.rbbackendcomite.models.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.edu.utez.rbbackcomite.models.role.RoleEntity;


@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    boolean existsByName(String name);
}