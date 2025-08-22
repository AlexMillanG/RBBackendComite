package mx.edu.utez.rbbackendcomite.models.group;

import mx.edu.utez.rbbackendcomite.models.user.UserResponseDto;

import java.util.List;

public class GroupResponseDto {
    private Long id;
    private String name;
    private String municipality;
    private String neighborhood;
    private List<UserResponseDto> members;

    public GroupResponseDto(GroupEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.municipality = entity.getMunicipality();
        this.neighborhood = entity.getNeighborhood();
        this.members = entity.getMembers().stream()
                .map(UserResponseDto::new)
                .toList();
    }
}
