package mx.edu.utez.rbbackendcomite.models.event;

import jakarta.persistence.*;
import lombok.Data;
import mx.edu.utez.rbbackendcomite.models.eventType.EventTypeEntity;
import mx.edu.utez.rbbackendcomite.models.group.GroupEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

   /*@ManyToOne
    @JoinTable(
            name = "event_participants",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserEntity> participants = new ArrayList<>();*/
   @ManyToOne
   @JoinColumn(name = "group_id")
   private GroupEntity group;
}
