package mx.edu.utez.rbbackendcomite.controller.group;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.edu.utez.rbbackendcomite.config.ApiResponseDto;
import mx.edu.utez.rbbackendcomite.models.group.GroupDto;
import mx.edu.utez.rbbackendcomite.services.group.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/groups")
@Tag(name = "Grupos", description = "Operaciones para la gestión de grupos")
public class GroupController {

    @Autowired
    private GroupService service;

    @Operation(summary = "Registrar un nuevo grupo")
    @ApiResponse(responseCode = "201", description = "Grupo creado correctamente",
            content = @Content(schema = @Schema(implementation = ApiResponseDto.class)))
    @PostMapping
    public ResponseEntity<ApiResponseDto> save(
            @RequestBody GroupDto dto) {
        return service.save(dto);
    }

    @Operation(summary = "Obtener todos los grupos")
    @ApiResponse(responseCode = "200", description = "Lista de grupos obtenida correctamente",
            content = @Content(schema = @Schema(implementation = ApiResponseDto.class)))
    @GetMapping
    public ResponseEntity<ApiResponseDto> getAll() {
        return service.getAll();
    }

    @Operation(summary = "Obtener un grupo por ID")
    @ApiResponse(responseCode = "200", description = "Grupo encontrado",
            content = @Content(schema = @Schema(implementation = ApiResponseDto.class)))
    @ApiResponse(responseCode = "404", description = "Grupo no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto> getOne(
            @Parameter(description = "ID del grupo") @PathVariable Long id) {
        return service.getOne(id);
    }

    @Operation(summary = "Eliminar un grupo")
    @ApiResponse(responseCode = "200", description = "Grupo eliminado correctamente",
            content = @Content(schema = @Schema(implementation = ApiResponseDto.class)))
    @ApiResponse(responseCode = "404", description = "Grupo no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto> delete(
            @Parameter(description = "ID del grupo a eliminar") @PathVariable Long id) {
        return service.delete(id);
    }

    @Operation(summary = "Actualizar un grupo")
    @ApiResponse(responseCode = "200", description = "Grupo actualizado correctamente",
            content = @Content(schema = @Schema(implementation = ApiResponseDto.class)))
    @ApiResponse(responseCode = "404", description = "Grupo no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto> update(
            @Parameter(description = "ID del grupo a actualizar") @PathVariable Long id,
            @RequestBody GroupDto dto) {
        return service.update(id, dto);
    }
}
