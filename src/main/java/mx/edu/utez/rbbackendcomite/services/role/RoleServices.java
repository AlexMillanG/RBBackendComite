package mx.edu.utez.rbbackendcomite.services.role;

import lombok.RequiredArgsConstructor;
import mx.edu.utez.rbbackendcomite.config.APIResponse;
import mx.edu.utez.rbbackendcomite.models.role.RoleDto;
import mx.edu.utez.rbbackendcomite.models.role.RoleEntity;
import mx.edu.utez.rbbackendcomite.models.role.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServices {

    private final RoleRepository repository;

    public ResponseEntity<APIResponse> getAll() {
        List<RoleEntity> roles = repository.findAll();
        return ResponseEntity.ok(new APIResponse("Roles encontrados", roles, false, HttpStatus.OK));
    }

    public ResponseEntity<APIResponse> getOne(Long id) {
        Optional<RoleEntity> found = repository.findById(id);
        if (found.isPresent()) {
            return ResponseEntity.ok(new APIResponse("Rol encontrado", found.get(), false, HttpStatus.OK));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new APIResponse("Rol no encontrado", null, true, HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<APIResponse> insert(RoleDto dto) {
        if (repository.existsByName(dto.getName())) {
            return ResponseEntity.badRequest()
                    .body(new APIResponse("Ya existe un rol con ese nombre", null, true, HttpStatus.BAD_REQUEST));
        }

        RoleEntity saved = repository.save(dto.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new APIResponse("Rol registrado correctamente", saved, false, HttpStatus.CREATED));
    }

    public ResponseEntity<APIResponse> update(Long id, RoleDto dto) {
        Optional<RoleEntity> found = repository.findById(id);
        if (found.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new APIResponse("Rol no encontrado", null, true, HttpStatus.NOT_FOUND));
        }

        RoleEntity entity = found.get();
        entity.setName(dto.getName());

        RoleEntity updated = repository.save(entity);
        return ResponseEntity.ok(new APIResponse("Rol actualizado correctamente", updated, false, HttpStatus.OK));
    }

    public ResponseEntity<APIResponse> delete(Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new APIResponse("Rol no encontrado", null, true, HttpStatus.NOT_FOUND));
        }

        repository.deleteById(id);
        return ResponseEntity.ok(new APIResponse("Rol eliminado correctamente", null, false, HttpStatus.OK));
    }
}
