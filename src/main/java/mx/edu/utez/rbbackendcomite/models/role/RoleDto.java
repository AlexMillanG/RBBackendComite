package mx.edu.utez.rbbackendcomite.models.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class RoleDto {
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 30, message = "El nombre no debe superar los 30 caracteres")
    private String name;

    public RoleEntity toEntity() {
        RoleEntity entity = new RoleEntity();
        entity.setId(this.id);
        entity.setName(this.name);
        return entity;
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