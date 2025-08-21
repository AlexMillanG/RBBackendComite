package mx.edu.utez.rbbackendcomite.security;

import mx.edu.utez.rbbackendcomite.config.ApiResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerValidationHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDto> handleException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(e ->
                errors.put(e.getField(), e.getDefaultMessage())
        );

        ApiResponseDto response = new ApiResponseDto(
                errors,
                true,
                "Favor de revisar la información",
                HttpStatus.BAD_REQUEST
        );
        return new ResponseEntity<>(response, response.getStatus());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponseDto> handleEnumDeserialization(HttpMessageNotReadableException ex) {
        String message = "Valor inválido para un campo enumerado. Asegúrate de usar uno de los valores permitidos.";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponseDto(null, true, message));
    }
}
