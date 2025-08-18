package mx.edu.utez.rbbackendcomite.controller.auth;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import mx.edu.utez.rbbackendcomite.config.ApiResponseDto;
import mx.edu.utez.rbbackendcomite.controller.auth.dto.LoginDto;
import mx.edu.utez.rbbackendcomite.services.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticación", description = "autenticación de usuarios")
public class AuthController {

    @Autowired
    private AuthService service;

    @Operation(
            summary = "Login de usuario",
            description = "Recibe las credenciales del usuario y devuelve un JWT si la autenticación es correcta",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Autenticación exitosa",
                            content = @Content(schema = @Schema(implementation = ApiResponseDto.class))
                    ),
                    @ApiResponse(responseCode = "401", description = "Credenciales inválidas", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", content = @Content)
            }
    )
    @PostMapping
    public ResponseEntity<ApiResponseDto> login(@Valid @RequestBody LoginDto loginDto) {
        System.out.println("Login recibido: " + loginDto.getUsername() + " / " + loginDto.getPassword());
        ApiResponseDto authService = service.doLogin(loginDto);
        return ResponseEntity.status(authService.getStatus()).body(authService);
    }


}
