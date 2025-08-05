package mx.edu.utez.rbbackendcomite.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.edu.utez.rbbackendcomite.config.ApiResponseDto;
import mx.edu.utez.rbbackendcomite.models.role.RoleDto;
import mx.edu.utez.rbbackendcomite.services.role.RoleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
@Tag(name = "Controlador de Roles", description = "Operaciones con roles de usuario")
//@SecurityRequirement(name = "bearerAuth")
public class RoleController {

    @Autowired
    private RoleServices service;

    @GetMapping("")
    @Operation(summary = "Obtener todos los roles", description = "Devuelve una lista de todos los roles registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de roles encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseDto.class)))
    })
    public ResponseEntity<ApiResponseDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un rol por ID", description = "Devuelve la información de un rol específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rol encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseDto.class)))
    })
    public ResponseEntity<ApiResponseDto> getOne(@PathVariable Long id) {
        return service.getOne(id);
    }

    @PostMapping("")
    @Operation(summary = "Registrar un nuevo rol", description = "Crea un nuevo rol en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Rol creado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Rol duplicado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseDto.class)))
    })
    public ResponseEntity<ApiResponseDto> insert(@RequestBody RoleDto dto) {
        return service.insert(dto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un rol", description = "Modifica un rol existente en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rol actualizado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseDto.class)))
    })
    public ResponseEntity<ApiResponseDto> update(@PathVariable Long id, @RequestBody RoleDto dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un rol", description = "Elimina un rol existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rol eliminado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiResponseDto.class)))
    })
    public ResponseEntity<ApiResponseDto> delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
