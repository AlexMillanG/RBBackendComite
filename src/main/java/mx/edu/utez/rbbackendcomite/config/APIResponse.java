package mx.edu.utez.rbbackendcomite.config;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

@Schema(description = "Respuesta estándar de la API")
public class APIResponse {

    @Schema(description = "Datos devueltos", example = "{}", nullable = true)
    private Object data;

    @Schema(description = "Indica si hubo un error", example = "false")
    private boolean error;

    @Schema(description = "Mensaje descriptivo", example = "Operación exitosa")
    private String message;

    @Schema(description = "Código HTTP de estado", example = "200")
    private HttpStatus status;

    public APIResponse(String message, boolean error, HttpStatus status) {
        this.message = message;
        this.error = error;
        this.status = status;
    }

    public APIResponse(String message, Object data, boolean error, HttpStatus status) {
        this.message = message;
        this.data = data;
        this.error = error;
        this.status = status;
    }

    public APIResponse(Object data, boolean error, String message) {
        this.data = data;
        this.error = error;
        this.message = message;
        this.status = error ? HttpStatus.BAD_REQUEST : HttpStatus.OK;
    }

    public APIResponse(Object data, boolean error, String message, HttpStatus status) {
        this.data = data;
        this.error = error;
        this.message = message;
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
