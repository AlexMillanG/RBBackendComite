package mx.edu.utez.rbbackendcomite.models.event;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import mx.edu.utez.rbbackendcomite.models.eventType.EventTypeEntity;
import mx.edu.utez.rbbackendcomite.models.group.GroupEntity;
import mx.edu.utez.rbbackendcomite.models.user.UserEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import mx.edu.utez.rbbackendcomite.models.userHasEvents.EventParticipantEntity;

@Entity
@Table(name="events")
@Data
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
    @JsonManagedReference

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventParticipantEntity> participants = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "group_id")
    @JsonIgnore
    private GroupEntity group;

}