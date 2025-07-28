package mx.edu.utez.rbbackendcomite.models.group;

import jakarta.validation.constraints.NotBlank;

public class GroupDto {
    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    @NotBlank(message = "El municipio es obligatorio")
    private String municipality;

    @NotBlank(message = "La colonia es obligatoria")
    private String neighborhood;

    public GroupEntity toEntity() {
        GroupEntity group = new GroupEntity();
        group.setName(this.name);
        group.setMunicipality(this.municipality);
        group.setNeighborhood(this.neighborhood);
        return group;
    }

    public GroupDto() {
    }

    public GroupDto(String name, String municipality, String neighborhood) {
        this.name = name;
        this.municipality = municipality;
        this.neighborhood = neighborhood;
    }

    public @NotBlank(message = "El nombre es obligatorio") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "El nombre es obligatorio") String name) {
        this.name = name;
    }

    public @NotBlank(message = "El municipio es obligatorio") String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(@NotBlank(message = "El municipio es obligatorio") String municipality) {
        this.municipality = municipality;
    }

    public @NotBlank(message = "La colonia es obligatoria") String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(@NotBlank(message = "La colonia es obligatoria") String neighborhood) {
        this.neighborhood = neighborhood;
    }
}