package mx.edu.utez.rbbackendcomite.controller.auth;

import jakarta.validation.Valid;
import mx.edu.utez.rbbackendcomite.config.ApiResponseDto;
import mx.edu.utez.rbbackendcomite.controller.auth.dto.LoginDto;
import mx.edu.utez.rbbackendcomite.services.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping
    public ResponseEntity<ApiResponseDto> login(@RequestBody @Valid LoginDto loginDto){
        ApiResponseDto authService = service.doLogin(loginDto);
        return ResponseEntity.status(authService.getStatus()).body(authService);
    }

}
