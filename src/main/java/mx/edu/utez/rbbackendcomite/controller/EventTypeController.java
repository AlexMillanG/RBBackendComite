package mx.edu.utez.rbbackendcomite.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.edu.utez.rbbackendcomite.config.ApiResponseDto;
import mx.edu.utez.rbbackendcomite.models.eventType.EventTypeDto;
import mx.edu.utez.rbbackendcomite.services.eventType.EventTypeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/eventtype")
@Tag(name = "Controlador de Tipos de Evento", description = "Operaciones relacionadas con los tipos de evento")
@SecurityRequirement(name = "barerAuth")
public class EventTypeController {

    @Autowired
    private EventTypeServices eventTypeServices;

    @GetMapping
    @Operation(summary = "Obtener todos los tipos de evento", description = "Obtiene todos los tipos de evento registrados.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tipos de evento obtenidos correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class)))
    })
    public ResponseEntity<ApiResponseDto> getAll() {
        return eventTypeServices.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un tipo de evento", description = "Obtiene un tipo de evento por su ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tipo de evento obtenido correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Tipo de evento no encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Error interno al obtener el tipo de evento",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class)))
    })
    public ResponseEntity<ApiResponseDto> getOne(@PathVariable Long id) {
        return eventTypeServices.getOne(id);
    }

    @PostMapping
    @Operation(summary = "Registrar nuevo tipo de evento", description = "Crea un nuevo tipo de evento.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Tipo de evento registrado correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Ya existe un tipo de evento con ese nombre",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))),
            @ApiResponse(responseCode = "500", description = "Error al registrar el tipo de evento",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class)))
    })
    public ResponseEntity<ApiResponseDto> save(@RequestBody EventTypeDto payload) {
        return eventTypeServices.insert(payload);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar tipo de evento", description = "Actualiza un tipo de evento existente por ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tipo de evento actualizado correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Tipo de evento no encontrado",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno al actualizar",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<ApiResponseDto> update(@PathVariable Long id, @RequestBody EventTypeDto payload) {
        return eventTypeServices.update(id, payload);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar tipo de evento", description = "Elimina un tipo de evento existente por ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Tipo de evento eliminado correctamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Tipo de evento no encontrado",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno al eliminar",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<ApiResponseDto> delete(@PathVariable Long id) {
        return eventTypeServices.delete(id);
    }
}
