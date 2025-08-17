package mx.edu.utez.rbbackendcomite.models.group;

import jakarta.persistence.*;
import mx.edu.utez.rbbackendcomite.models.event.EventEntity;
import mx.edu.utez.rbbackendcomite.models.user.UserEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_groups")

public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String municipality;
    private String neighborhood;

   @OneToMany(mappedBy = "group")
    private List<UserEntity> members = new ArrayList<>();

    @OneToMany(mappedBy = "group")
    private List<EventEntity> events = new ArrayList<>();

    public GroupEntity(Long id, String name, String municipality, String neighborhood, List<UserEntity> members, List<EventEntity> events) {
        this.id = id;
        this.name = name;
        this.municipality = municipality;
        this.neighborhood = neighborhood;
        this.members = members;
        this.events = events;
    }

    public GroupEntity() {
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

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public List<UserEntity> getMembers() {
        return members;
    }

    public void setMembers(List<UserEntity> members) {
        this.members = members;
    }

    public List<EventEntity> getEvents() {
        return events;
    }

    public void setEvents(List<EventEntity> events) {
        this.events = events;
    }
}