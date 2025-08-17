package mx.edu.utez.rbbackendcomite.services.group;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mx.edu.utez.rbbackendcomite.config.ApiResponseDto;
import mx.edu.utez.rbbackendcomite.models.group.GroupDto;
import mx.edu.utez.rbbackendcomite.models.group.GroupEntity;
import mx.edu.utez.rbbackendcomite.models.group.GroupRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    public ResponseEntity<ApiResponseDto> save(@Valid GroupDto dto) {
        // Validar que no exista un grupo con el mismo nombre (opcional)
        if (groupRepository.existsByName(dto.getName())) {
            return new ResponseEntity<>(
                    new ApiResponseDto(null, true, "Ya existe un grupo con ese nombre"),
                    HttpStatus.BAD_REQUEST);
        }

        GroupEntity group = dto.toEntity();

        return new ResponseEntity<>(
                new ApiResponseDto(groupRepository.save(group), false, "Grupo registrado correctamente"),
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<ApiResponseDto> getAll() {
        return new ResponseEntity<>(new ApiResponseDto(groupRepository.findAll(), false, "OK"), HttpStatus.OK);
    }

    public ResponseEntity<ApiResponseDto> getOne(Long id) {
        Optional<GroupEntity> optionalGroup = groupRepository.findById(id);
        if (optionalGroup.isEmpty())
            return new ResponseEntity<>(new ApiResponseDto(null, true, "Grupo no encontrado"), HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(new ApiResponseDto(optionalGroup.get(), false, "OK"), HttpStatus.OK);
    }

    public ResponseEntity<ApiResponseDto> update(Long id, @Valid GroupDto dto) {
        Optional<GroupEntity> optionalGroup = groupRepository.findById(id);
        if (optionalGroup.isEmpty())
            return new ResponseEntity<>(new ApiResponseDto(null, true, "Grupo no encontrado"), HttpStatus.NOT_FOUND);

        GroupEntity group = optionalGroup.get();

        // Validar que el nuevo nombre no choque con otro grupo distinto
        if (!group.getName().equals(dto.getName()) && groupRepository.existsByName(dto.getName())) {
            return new ResponseEntity<>(
                    new ApiResponseDto(null, true, "Ya existe otro grupo con ese nombre"),
                    HttpStatus.BAD_REQUEST);
        }

        group.setName(dto.getName());
        group.setMunicipality(dto.getMunicipality());
        group.setNeighborhood(dto.getNeighborhood());

        return new ResponseEntity<>(
                new ApiResponseDto(groupRepository.save(group), false, "Grupo actualizado correctamente"),
                HttpStatus.OK
        );
    }

    public ResponseEntity<ApiResponseDto> delete(Long id) {
        Optional<GroupEntity> optionalGroup = groupRepository.findById(id);
        if (optionalGroup.isEmpty())
            return new ResponseEntity<>(new ApiResponseDto(null, true, "Grupo no encontrado"), HttpStatus.NOT_FOUND);

        groupRepository.deleteById(id);
        return new ResponseEntity<>(new ApiResponseDto(null, false, "Grupo eliminado correctamente"), HttpStatus.OK);
    }
}