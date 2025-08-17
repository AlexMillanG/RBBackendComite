package mx.edu.utez.rbbackendcomite.models.event;

import jakarta.persistence.*;
import mx.edu.utez.rbbackendcomite.models.eventType.EventTypeEntity;
import mx.edu.utez.rbbackendcomite.models.group.GroupEntity;
import mx.edu.utez.rbbackendcomite.models.user.UserEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="events")

public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private EventTypeEntity type;

    @ManyToMany
    @JoinTable(
            name = "event_participants",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserEntity> participants = new ArrayList<>();



    @ManyToOne
   @JoinColumn(name = "group_id")
   private GroupEntity group;

    public EventEntity(Long id, String title, LocalDate date, EventStatus status, EventTypeEntity type, List<UserEntity> participants, GroupEntity group) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.status = status;
        this.type = type;
        this.participants = participants;
        this.group = group;
    }

    public EventEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public EventStatus getStatus() {
        return status;
    }

    public void setStatus(EventStatus status) {
        this.status = status;
    }

    public EventTypeEntity getType() {
        return type;
    }

    public void setType(EventTypeEntity type) {
        this.type = type;
    }

    public List<UserEntity> getParticipants() {
        return participants;
    }

    public void setParticipants(List<UserEntity> participants) {
        this.participants = participants;
    }

    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }
}
