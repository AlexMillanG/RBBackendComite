package mx.edu.utez.rbbackendcomite.services.event;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mx.edu.utez.rbbackendcomite.config.ApiResponseDto;
import mx.edu.utez.rbbackendcomite.models.event.EventDto;
import mx.edu.utez.rbbackendcomite.models.event.EventEntity;
import mx.edu.utez.rbbackendcomite.models.event.EventRepository;
import mx.edu.utez.rbbackendcomite.models.event.EventStatus;
import mx.edu.utez.rbbackendcomite.models.eventType.EventTypeRepository;
import mx.edu.utez.rbbackendcomite.models.group.GroupRepository;
import mx.edu.utez.rbbackendcomite.models.user.UserEntity;
import mx.edu.utez.rbbackendcomite.models.user.UserRepository;
import mx.edu.utez.rbbackendcomite.models.userHasEvents.EventParticipantEntity;
import mx.edu.utez.rbbackendcomite.models.userHasEvents.EventParticipantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class EventService {

    private final EventRepository repository;
    private final EventTypeRepository eventTypeRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final EventParticipantRepository participantRepository;



    public ResponseEntity<ApiResponseDto> getAll() {
        List<EventEntity> events = repository.findAll();
        return ResponseEntity.ok(new ApiResponseDto(events, false, "Eventos encontrados"));
    }

    public ResponseEntity<ApiResponseDto> getOne(Long id) {
        Optional<EventEntity> found = repository.findById(id);
        return found.map(event -> ResponseEntity.ok(new ApiResponseDto(event, false, "Evento encontrado")))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponseDto(null, true, "Evento no encontrado")));
    }

    public ResponseEntity<ApiResponseDto> create(EventDto dto) {
        var eventType = eventTypeRepository.findById(dto.getTypeId());
        if (eventType.isEmpty()) {
            return ResponseEntity.badRequest().body(new ApiResponseDto(null, true, "Tipo de evento no encontrado"));
        }

        var group = groupRepository.findById(dto.getGroupId());
        if (group.isEmpty()) {
            return ResponseEntity.badRequest().body(new ApiResponseDto(null, true, "Grupo de evento no encontrado"));
        }

        EventEntity event = new EventEntity();
        event.setTitle(dto.getTitle());
        event.setDate(dto.getDate());
        event.setStatus(EventStatus.valueOf(dto.getStatus()));
        event.setType(eventType.get());
        event.setGroup(group.get());

        EventEntity saved = repository.save(event);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponseDto(saved, false, "Evento registrado correctamente"));
    }

    public ResponseEntity<ApiResponseDto> update(Long id, EventDto dto) {
        Optional<EventEntity> found = repository.findById(id);
        if (found.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponseDto(null, true, "Evento no encontrado"));
        }

        var eventType = eventTypeRepository.findById(dto.getTypeId());
        if (eventType.isEmpty()) {
            return ResponseEntity.badRequest().body(new ApiResponseDto(null, true, "Tipo de evento no encontrado"));
        }

        var group = groupRepository.findById(dto.getGroupId());
        if (group.isEmpty()) {
            return ResponseEntity.badRequest().body(new ApiResponseDto(null, true, "Grupo no encontrado"));
        }

        EventEntity entity = found.get();
        entity.setTitle(dto.getTitle());
        entity.setDate(dto.getDate());
        entity.setStatus(EventStatus.valueOf(dto.getStatus()));
        entity.setType(eventType.get());
        entity.setGroup(group.get());

        EventEntity updated = repository.save(entity);
        return ResponseEntity.ok(new ApiResponseDto(updated, false, "Evento actualizado correctamente"));
    }

    public ResponseEntity<ApiResponseDto> delete(Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponseDto(null, true, "Evento no encontrado"));
        }

        repository.deleteById(id);
        return ResponseEntity.ok(new ApiResponseDto(null, false, "Evento eliminado correctamente"));
    }

    public ResponseEntity<ApiResponseDto> getEventsByStatus(String status) {
        try {
            EventStatus eventStatus = EventStatus.valueOf(status);
            List<EventEntity> events = repository.findByStatus(eventStatus);
            return ResponseEntity.ok(new ApiResponseDto(events, false, "Eventos encontrados por estado"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto(null, true, "Estado de evento no válido"));
        }
    }

    public ResponseEntity<ApiResponseDto> getEventsByGroupId(Long groupId) {
        if (!groupRepository.existsById(groupId)) {
            return ResponseEntity.badRequest().body(new ApiResponseDto(null, true, "Grupo no encontrado"));
        }
        List<EventEntity> events = repository.findByGroupId(groupId);
        return ResponseEntity.ok(new ApiResponseDto(events, false, "Eventos del grupo encontrados"));
    }

    public ResponseEntity<ApiResponseDto> getEventsByTypeId(Long typeId) {
        if (!eventTypeRepository.existsById(typeId)) {
            return ResponseEntity.badRequest().body(new ApiResponseDto(null, true, "Tipo de evento no encontrado"));
        }
        List<EventEntity> events = repository.findByTypeId(typeId);
        return ResponseEntity.ok(new ApiResponseDto(events, false, "Eventos del tipo encontrados"));
    }

    public ResponseEntity<ApiResponseDto> setUsersToEvent(Long eventId, List<Long> userIds) {
        Optional<EventEntity> optionalEvent = repository.findById(eventId);
        if (optionalEvent.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponseDto(null, true, "Evento no encontrado"));
        }

        EventEntity event = optionalEvent.get();
        List<EventParticipantEntity> participants = new ArrayList<>();

        for (Long userId : userIds) {
            Optional<UserEntity> optionalUser = userRepository.findById(userId);
            if (optionalUser.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(new ApiResponseDto(null, true, "Usuario con ID " + userId + " no encontrado"));
            }

            UserEntity user = optionalUser.get();

            // Verificamos si ya existe relación usuario-evento
            Optional<EventParticipantEntity> existing = participantRepository.findByEventAndUser(event, user);
            if (existing.isEmpty()) {
                EventParticipantEntity participant = new EventParticipantEntity();
                participant.setEvent(event);
                participant.setUser(user);
                participant.setConfirmed(false);
                participants.add(participant);
            }
        }

        if (!participants.isEmpty()) {
            participantRepository.saveAll(participants);
        }

        return ResponseEntity.ok(
                new ApiResponseDto(null, false, "Usuarios asignados al evento correctamente")
        );
    }

    public  ResponseEntity<ApiResponseDto> getEventsByUserService(Long userId) {
        Optional<UserEntity> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponseDto(null, true, "Usuario no encontrado"));
        }

        UserEntity user = optionalUser.get();
        List<EventParticipantEntity> participations = participantRepository.findByUser(user);

        List<EventEntity> events = participations.stream()
                .map(EventParticipantEntity::getEvent)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new ApiResponseDto(events, false, "Eventos encontrados para el usuario"));
    }

    public ResponseEntity<ApiResponseDto> setAttendance(Long eventId, Long userId, boolean confirmed) {
        Optional<EventEntity> optionalEvent = repository.findById(eventId);
        if (optionalEvent.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponseDto(null, true, "Evento no encontrado"));
        }

        Optional<UserEntity> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponseDto(null, true, "Usuario no encontrado"));
        }

        EventEntity event = optionalEvent.get();
        UserEntity user = optionalUser.get();

        Optional<EventParticipantEntity> optionalParticipant = participantRepository.findByEventAndUser(event, user);
        if (optionalParticipant.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponseDto(null, true, "El usuario no está asignado a este evento"));
        }

        EventParticipantEntity participant = optionalParticipant.get();
        participant.setConfirmed(confirmed);
        participantRepository.save(participant);

        String message = confirmed ? "Asistencia confirmada correctamente" : "Confirmación cancelada correctamente";

        return ResponseEntity.ok(
                new ApiResponseDto(participant, false, message)
        );
    }




}
