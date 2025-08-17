package mx.edu.utez.rbbackendcomite.controller.event;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import mx.edu.utez.rbbackendcomite.config.ApiResponseDto;
import mx.edu.utez.rbbackendcomite.models.event.EventDto;
import mx.edu.utez.rbbackendcomite.services.event.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/event")
@Tag(name = "Eventos", description = "Operaciones relacionadas con la gestión de eventos")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @Operation(summary = "Obtener todos los eventos", description = "Retorna la lista completa de eventos")
    @ApiResponse(responseCode = "200", description = "Lista de eventos obtenida correctamente",
            content = @Content(schema = @Schema(implementation = ApiResponseDto.class)))
    @GetMapping("")
    public ResponseEntity<ApiResponseDto> findAll() {
        return eventService.getAll();
    }

    @Operation(summary = "Obtener un evento por ID")
    @ApiResponse(responseCode = "200", description = "Evento encontrado",
            content = @Content(schema = @Schema(implementation = ApiResponseDto.class)))
    @ApiResponse(responseCode = "404", description = "Evento no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto> findById(
            @Parameter(description = "ID del evento") @PathVariable Long id) {
        return eventService.getOne(id);
    }

    @Operation(summary = "Registrar un nuevo evento")
    @ApiResponse(responseCode = "201", description = "Evento creado exitosamente",
            content = @Content(schema = @Schema(implementation = ApiResponseDto.class)))
    @PostMapping("")
    public ResponseEntity<ApiResponseDto> save(
            @RequestBody @Valid EventDto payload) {
        return eventService.create(payload);
    }

    @Operation(summary = "Actualizar un evento existente")
    @ApiResponse(responseCode = "200", description = "Evento actualizado correctamente",
            content = @Content(schema = @Schema(implementation = ApiResponseDto.class)))
    @ApiResponse(responseCode = "404", description = "Evento no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto> update(
            @RequestBody @Valid EventDto payload,
            @Parameter(description = "ID del evento a actualizar") @PathVariable Long id) {
        return eventService.update(id, payload);
    }

    @Operation(summary = "Eliminar un evento por ID")
    @ApiResponse(responseCode = "200", description = "Evento eliminado correctamente",
            content = @Content(schema = @Schema(implementation = ApiResponseDto.class)))
    @ApiResponse(responseCode = "404", description = "Evento no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto> delete(
            @Parameter(description = "ID del evento a eliminar") @PathVariable Long id) {
        return eventService.delete(id);
    }

    @Operation(summary = "Obtener eventos por estatus")
    @ApiResponse(responseCode = "200", description = "Eventos filtrados por estatus",
            content = @Content(schema = @Schema(implementation = ApiResponseDto.class)))
    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponseDto> findByStatus(
            @Parameter(description = "Estatus del evento (ejemplo: ACTIVO, CANCELADO)") @PathVariable String status) {
        return eventService.getEventsByStatus(status);
    }

    @Operation(summary = "Obtener eventos por grupo")
    @ApiResponse(responseCode = "200", description = "Eventos filtrados por grupo",
            content = @Content(schema = @Schema(implementation = ApiResponseDto.class)))
    @GetMapping("/group/{groupId}")
    public ResponseEntity<ApiResponseDto> findByGroupId(
            @Parameter(description = "ID del grupo") @PathVariable Long groupId) {
        return eventService.getEventsByGroupId(groupId);
    }

    @Operation(summary = "Obtener eventos por tipo")
    @ApiResponse(responseCode = "200", description = "Eventos filtrados por tipo",
            content = @Content(schema = @Schema(implementation = ApiResponseDto.class)))
    @GetMapping("/type/{typeId}")
    public ResponseEntity<ApiResponseDto> findByTypeId(
            @Parameter(description = "ID del tipo de evento") @PathVariable Long typeId) {
        return eventService.getEventsByTypeId(typeId);
    }

    @Operation(summary = "Asignar usuarios a un evento")
    @ApiResponse(responseCode = "200", description = "Usuarios asignados correctamente",
            content = @Content(schema = @Schema(implementation = ApiResponseDto.class)))
    @ApiResponse(responseCode = "404", description = "Evento no encontrado")
    @PostMapping("/setUsersToEvent/{eventId}")
    public ResponseEntity<ApiResponseDto> setUsersToEvent(
            @Parameter(description = "ID del evento") @PathVariable Long eventId,
            @RequestBody List<Long> userIds) {
        return eventService.setUsersToEvent(eventId, userIds);
    }
}
