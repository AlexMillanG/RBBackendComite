package mx.edu.utez.rbbackendcomite.models.userHasEvents;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import mx.edu.utez.rbbackendcomite.models.event.EventEntity;
import mx.edu.utez.rbbackendcomite.models.user.UserEntity;

@Entity
@Table(name = "event_participants")
@Data
public class EventParticipantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relación hacia evento
    @ManyToOne
    @JoinColumn(name = "event_id")
    @JsonIgnoreProperties("participants") // Evita recursión
    private EventEntity event;

    // Relación hacia usuario
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"events", "group", "role"}) // Excluye propiedades que puedan causar recursión
    private UserEntity user;

    private Boolean confirmed = false;
}