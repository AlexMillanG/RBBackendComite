package mx.edu.utez.rbbackendcomite.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import mx.edu.utez.rbbackendcomite.config.APIResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
                            @Content(mediaType = "application/json", schema = @Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = APIResponse.class))

                    }
            )
    })
    public ResponseEntity<APIResponse> getAll() {
        ResponseEntity<APIResponse> entity = eventTypeServices.getAll();
        APIResponse response = entity.getBody();
        return new ResponseEntity<>(response, response.getStatus());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtencion de una sola Cede", description = "Mandar a traer una sola Cede con uso del id dentro del sistema.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Obtencion de una sola Cede",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = APIResponse.class))

                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No se logro encontrar la Cede",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = APIResponse.class))

                    }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "No se pudo consultar la Cede",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = APIResponse.class)),
                            @Content(mediaType = "application/xml", schema = @Schema(implementation = APIResponse.class))

                    }
            )
    })
    public ResponseEntity<APIResponse> getOne(@PathVariable("id") Long id) {
        return eventTypeServices.getOne(id);
    }

}
