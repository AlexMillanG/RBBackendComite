package mx.edu.utez.rbbackendcomite.config;

import org.springframework.http.HttpStatus;

public class ApiResponseDto {

    private Object data;
    private boolean error;
    private String message;
    private HttpStatus status;

    public ApiResponseDto(Object data, boolean error, String message, HttpStatus status) {
        this.data = data;
        this.error = error;
        this.message = message;
        this.status = status;
    }

    public ApiResponseDto(Object data, boolean error, String message) {
        this(data, error, message, error ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
    }

    public ApiResponseDto(String message, boolean error, HttpStatus status) {
        this(null, error, message, status);
    }

    public ApiResponseDto(String message, boolean error) {
        this(null, error, message, error ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
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
