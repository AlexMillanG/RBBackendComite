package mx.edu.utez.rbbackendcomite.services.role;

import lombok.RequiredArgsConstructor;

import mx.edu.utez.rbbackendcomite.config.ApiResponseDto;
import mx.edu.utez.rbbackendcomite.models.role.RoleDto;
import mx.edu.utez.rbbackendcomite.models.role.RoleEntity;
import mx.edu.utez.rbbackendcomite.models.role.RoleRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServices {

    private final RoleRepository repository;

    public ResponseEntity<ApiResponseDto> getAll() {
        List<RoleEntity> roles = repository.findAll();
        return ResponseEntity.ok(new ApiResponseDto(roles, false, "Roles encontrados"));
    }

    public ResponseEntity<ApiResponseDto> getOne(Long id) {
        Optional<RoleEntity> found = repository.findById(id);
        if (found.isPresent()) {
            return ResponseEntity.ok(new ApiResponseDto(found.get(), false, "Rol encontrado"));
        }
        return ResponseEntity.status(404).body(new ApiResponseDto(null, true, "Rol no encontrado"));
    }

    public ResponseEntity<ApiResponseDto> insert(RoleDto dto) {
        if (repository.existsByName(dto.getName())) {
            return ResponseEntity.badRequest().body(new ApiResponseDto(null, true, "Ya existe un rol con ese nombre"));
        }

        RoleEntity saved = repository.save(dto.toEntity());
        return ResponseEntity.status(201).body(new ApiResponseDto(saved, false, "Rol registrado correctamente"));
    }

    public ResponseEntity<ApiResponseDto> update(Long id, RoleDto dto) {
        Optional<RoleEntity> found = repository.findById(id);
        if (found.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponseDto(null, true, "Rol no encontrado"));
        }

        RoleEntity entity = found.get();
        entity.setName(dto.getName());

        RoleEntity updated = repository.save(entity);
        return ResponseEntity.ok(new ApiResponseDto(updated, false, "Rol actualizado correctamente"));
    }

    public ResponseEntity<ApiResponseDto> delete(Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.status(404).body(new ApiResponseDto(null, true, "Rol no encontrado"));
        }

        repository.deleteById(id);
        return ResponseEntity.ok(new ApiResponseDto(null, false, "Rol eliminado correctamente"));
    }
}