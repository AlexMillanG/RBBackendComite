package mx.edu.utez.rbbackendcomite.services.eventType;

import lombok.RequiredArgsConstructor;

import mx.edu.utez.rbbackendcomite.config.ApiResponseDto;
import mx.edu.utez.rbbackendcomite.models.eventType.EventTypeDto;
import mx.edu.utez.rbbackendcomite.models.eventType.EventTypeEntity;
import mx.edu.utez.rbbackendcomite.models.eventType.EventTypeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventTypeServices {
    private final EventTypeRepository repository;

    public ResponseEntity<ApiResponseDto> getAll() {
        List<EventTypeEntity> types = repository.findAll();
        return ResponseEntity.ok(new ApiResponseDto(types, false, "Tipos de evento encontrados"));
    }

    public ResponseEntity<ApiResponseDto> getOne(Long id) {
        Optional<EventTypeEntity> found = repository.findById(id);
        if (found.isPresent()) {
            return ResponseEntity.ok(new ApiResponseDto(found.get(), false, "Tipo de evento encontrado"));
        }
        return ResponseEntity.status(404).body(new ApiResponseDto(null, true, "Tipo de evento no encontrado"));
    }

    public ResponseEntity<ApiResponseDto> insert(EventTypeDto dto) {
        if (repository.existsByName(dto.getName())) {
            return ResponseEntity.badRequest().body(new ApiResponseDto(null, true, "Ya existe un tipo de evento con ese nombre"));
        }

        EventTypeEntity saved = repository.save(dto.toEntity());
        return ResponseEntity.status(201).body(new ApiResponseDto(saved, false, "Tipo de evento registrado correctamente"));
    }

    public ResponseEntity<ApiResponseDto> update(Long id, EventTypeDto dto) {
        Optional<EventTypeEntity> found = repository.findById(id);
        if (found.isEmpty()) {
            return ResponseEntity.status(404).body(new ApiResponseDto(null, true, "Tipo de evento no encontrado"));
        }

        EventTypeEntity entity = found.get();
        entity.setName(dto.getName());

        EventTypeEntity updated = repository.save(entity);
        return ResponseEntity.ok(new ApiResponseDto(updated, false, "Tipo de evento actualizado correctamente"));
    }

    public ResponseEntity<ApiResponseDto> delete(Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.status(404).body(new ApiResponseDto(null, true, "Tipo de evento no encontrado"));
        }

        repository.deleteById(id);
        return ResponseEntity.ok(new ApiResponseDto(null, false, "Tipo de evento eliminado correctamente"));
    }

}
