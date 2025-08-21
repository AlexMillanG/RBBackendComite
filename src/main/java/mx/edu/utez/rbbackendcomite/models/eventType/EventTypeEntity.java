package mx.edu.utez.rbbackendcomite.models.eventType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import mx.edu.utez.rbbackendcomite.models.event.EventEntity;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "event_types")

public class EventTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // "Limpieza", "Reforestación", etc.

    @JsonIgnore
    @OneToMany(mappedBy = "type")
    private List<EventEntity> events = new ArrayList<>();

    public EventTypeEntity(Long id, String name, List<EventEntity> events) {
        this.id = id;
        this.name = name;
        this.events = events;
    }

    public EventTypeEntity() {
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

    public List<EventEntity> getEvents() {
        return events;
    }

    public void setEvents(List<EventEntity> events) {
        this.events = events;
    }
}
