package mx.edu.utez.rbbackendcomite.models.eventType;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EventTypeDto {
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 50, message = "El nombre no debe superar los 50 caracteres")
    private String name;

    public EventTypeEntity toEntity() {
        EventTypeEntity entity = new EventTypeEntity();
        entity.setId(this.id);
        entity.setName(this.name);
        return entity;
    }

    public EventTypeDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public EventTypeDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
