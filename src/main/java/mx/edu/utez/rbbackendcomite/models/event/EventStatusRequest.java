package mx.edu.utez.rbbackendcomite.models.event;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class EventStatusRequest {
    @NotBlank(message = "El estatus es requerido")
    @Pattern(regexp = "PRÓXIMAMENTE|EN_EJECUCIÓN|FINALIZADO", 
             message = "El estatus debe ser: PRÓXIMAMENTE, EN_EJECUCIÓN o FINALIZADO")
    private String status;
}