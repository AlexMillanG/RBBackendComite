package mx.edu.utez.rbbackendcomite.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import mx.edu.utez.rbbackendcomite.config.ApiResponseDto;
import mx.edu.utez.rbbackendcomite.models.eventType.EventTypeDto;
import mx.edu.utez.rbbackendcomite.services.eventType.EventTypeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/eventtype")
@Tag(name = "Controlador de Event Tyoe" , description = "Operaciones realizadas con los event type")
@SecurityRequirement(name = "barerAuth")
public class eventTypeController {

    @Autowired
    private EventTypeServices eventTypeServices;

    @GetMapping
    @Operation(summary = "Obtencion de todos los event types", description = "Mandar a traer todos las cedes registrados en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Obtencion de todos las Cedes",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = ApiResponseDto.class))

                    }
            )
    })
    public ResponseEntity<ApiResponseDto> getAll() {
        ResponseEntity<ApiResponseDto> entity = eventTypeServices.getAll();
        ApiResponseDto response = entity.getBody();
        return new ResponseEntity<>(response, response.getStatus());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtencion de un solo tipo de evento", description = "Mandar a traer una sola Cede con uso del id dentro del sistema.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Obtencion de una sola Cede",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = ApiResponseDto.class))

                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se logro encontrar la Cede",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = ApiResponseDto.class))

                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "No se pudo consultar la Cede",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = ApiResponseDto.class))

                    }
            )
    })
    public ResponseEntity<ApiResponseDto> getOne(@PathVariable("id") Long id) {
        return eventTypeServices.getOne(id);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo tipo de evento", description = "Registra un nuevo tipo de evento en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Registro exitoso del tipo de evento",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = ApiResponseDto.class))
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Ya existe un tipo de evento con ese nombre",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error al registrar el tipo de evento",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ApiResponseDto.class))
            )
    })
    public ResponseEntity<ApiResponseDto> save(@RequestBody EventTypeDto payload) {
        ResponseEntity<ApiResponseDto> entity = eventTypeServices.insert(payload);
        ApiResponseDto response = entity.getBody();
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar tipo de evento", description = "Actualiza un tipo de evento existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de evento actualizado correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Tipo de evento no encontrado",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<ApiResponseDto> update(@PathVariable Long id, @RequestBody EventTypeDto payload) {
        return eventTypeServices.update(id, payload);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar tipo de evento", description = "Elimina un tipo de evento por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de evento eliminado correctamente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Tipo de evento no encontrado",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<ApiResponseDto> delete(@PathVariable Long id) {
        return eventTypeServices.delete(id);
    }
}
