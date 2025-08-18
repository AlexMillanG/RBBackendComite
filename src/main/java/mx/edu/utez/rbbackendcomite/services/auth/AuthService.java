package mx.edu.utez.rbbackendcomite.services.auth;

import mx.edu.utez.rbbackendcomite.config.ApiResponseDto;
import mx.edu.utez.rbbackendcomite.controller.auth.dto.LoginDto;
import mx.edu.utez.rbbackendcomite.models.user.UserEntity;
import mx.edu.utez.rbbackendcomite.models.user.UserRepository;
import mx.edu.utez.rbbackendcomite.security.jwt.JWTUtils;
import mx.edu.utez.rbbackendcomite.security.jwt.UDService;
import mx.edu.utez.rbbackendcomite.utils.PasswordEncoderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UDService udService;

    @Autowired
    private JWTUtils jwtUtils;

    @Transactional(readOnly = true)
    public ApiResponseDto doLogin(LoginDto payload) {
        try {
            UserEntity found = userRepository.findByUsername(payload.getUsername()).orElse(null);
            if (found == null) return new ApiResponseDto("Usuario no encontrado", true, HttpStatus.NOT_FOUND);

            if (!PasswordEncoderService.verifyPassword(payload.getPassword(), found.getPassword()))
                return new ApiResponseDto("Las contrasenas no coniciden", true, HttpStatus.BAD_REQUEST);

            UserDetails ud = udService.loadUserByUsername(found.getUsername());
            String token = jwtUtils.generateToken(ud);
            return new ApiResponseDto(token,  false,"Operacion exitosa", HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ApiResponseDto(
                    "Error al iniciar sesion",
                    true,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @Transactional(rollbackFor = {SQLException.class,Exception.class})
    public ApiResponseDto register (UserEntity payload){
        try {
            UserEntity found= userRepository.findByUsername(payload.getUsername()).orElse(null);
            if(found != null) return new ApiResponseDto("El usuario ya existe", true, HttpStatus.BAD_REQUEST);

            payload.setPassword(PasswordEncoderService.encodePassword(payload.getPassword()));
            userRepository.save(payload);
            return new ApiResponseDto("Operación exitosa", false, HttpStatus.CREATED);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ApiResponseDto(
                    "Error al registrar al usuario",
                    true,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}