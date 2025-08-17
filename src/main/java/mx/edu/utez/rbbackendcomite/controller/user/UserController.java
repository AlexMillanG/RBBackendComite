package mx.edu.utez.rbbackendcomite.controller.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.edu.utez.rbbackendcomite.config.ApiResponseDto;
import mx.edu.utez.rbbackendcomite.models.user.UserDto;
import mx.edu.utez.rbbackendcomite.services.user.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Usuarios", description = "Operaciones para la gestión de usuarios")
public class UserController {

    @Autowired
    private UserServices service;

    @Operation(summary = "Obtener todos los usuarios")
    @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida correctamente",
            content = @Content(schema = @Schema(implementation = ApiResponseDto.class)))
    @GetMapping
    public ResponseEntity<ApiResponseDto> findAll() {
        return service.getAll();
    }

    @Operation(summary = "Obtener un usuario por ID")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado",
            content = @Content(schema = @Schema(implementation = ApiResponseDto.class)))
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto> findOne(
            @Parameter(description = "ID del usuario") @PathVariable Long id) {
        return service.getOne(id);
    }

    @Operation(summary = "Actualizar un usuario")
    @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente",
            content = @Content(schema = @Schema(implementation = ApiResponseDto.class)))
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDto> update(
            @Parameter(description = "ID del usuario a actualizar") @PathVariable Long id,
            @RequestBody UserDto dto) {
        return service.update(id, dto);
    }

    @Operation(summary = "Eliminar un usuario")
    @ApiResponse(responseCode = "200", description = "Usuario eliminado correctamente",
            content = @Content(schema = @Schema(implementation = ApiResponseDto.class)))
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDto> delete(
            @Parameter(description = "ID del usuario a eliminar") @PathVariable Long id) {
        return service.delete(id);
    }

    @Operation(summary = "Obtener usuarios por grupo")
    @ApiResponse(responseCode = "200", description = "Usuarios filtrados por grupo",
            content = @Content(schema = @Schema(implementation = ApiResponseDto.class)))
    @GetMapping("usersByGroup/{groupId}")
    public ResponseEntity<ApiResponseDto> getUsersByGroup(
            @Parameter(description = "ID del grupo") @PathVariable Long groupId) {
        return service.getUsersByGroup(groupId);
    }

    @Operation(summary = "Obtener usuarios por rol")
    @ApiResponse(responseCode = "200", description = "Usuarios filtrados por rol",
            content = @Content(schema = @Schema(implementation = ApiResponseDto.class)))
    @GetMapping("usersByRole/{roleId}")
    public ResponseEntity<ApiResponseDto> getUsersByRole(
            @Parameter(description = "ID del rol") @PathVariable Long roleId) {
        return service.getUsersByRole(roleId);
    }
}
