package mx.edu.utez.rbbackendcomite.services.eventType;

import mx.edu.utez.rbbackendcomite.config.ApiResponseDto;
import mx.edu.utez.rbbackendcomite.models.eventType.EventTypeDto;
import mx.edu.utez.rbbackendcomite.models.eventType.EventTypeEntity;
import mx.edu.utez.rbbackendcomite.models.eventType.EventTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventTypeServices {

    @Autowired
    private EventTypeRepository repository;

    public ResponseEntity<ApiResponseDto> getAll() {
        List<EventTypeEntity> types = repository.findAll();
        return ResponseEntity.ok(new ApiResponseDto( types, false,"Tipos de evento encontrados", HttpStatus.OK));
    }

    public ResponseEntity<ApiResponseDto> getOne(Long id) {
        Optional<EventTypeEntity> found = repository.findById(id);
        if (found.isPresent()) {
            return ResponseEntity.ok(new ApiResponseDto( found.get(), false,"Tipo de evento encontrado", HttpStatus.OK));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponseDto("Tipo de evento no encontrado",  true, HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<ApiResponseDto> insert(EventTypeDto dto) {
        if (repository.existsByName(dto.getName())) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponseDto("Ya existe un tipo de evento con ese nombre",  true, HttpStatus.BAD_REQUEST));
        }

        EventTypeEntity saved = repository.save(dto.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponseDto( saved, false,"Tipo de evento registrado correctamente", HttpStatus.CREATED));
    }

    public ResponseEntity<ApiResponseDto> update(Long id, EventTypeDto dto) {
        Optional<EventTypeEntity> found = repository.findById(id);
        if (found.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponseDto("Tipo de evento no encontrado",  true, HttpStatus.NOT_FOUND));
        }

        EventTypeEntity entity = found.get();
        entity.setName(dto.getName());
        EventTypeEntity updated = repository.save(entity);

        return ResponseEntity.ok(new ApiResponseDto( updated, false,"Tipo de evento actualizado correctamente", HttpStatus.OK));
    }

    public ResponseEntity<ApiResponseDto> delete(Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponseDto("Tipo de evento no encontrado",true, HttpStatus.NOT_FOUND));
        }
        EventTypeEntity deleted = repository.findById(id).orElseThrow();
        repository.deleteById(id);
        return ResponseEntity.ok(new ApiResponseDto(deleted,  false,"Tipo de evento eliminado correctamente", HttpStatus.OK));
    }
}
