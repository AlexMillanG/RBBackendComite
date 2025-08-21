package mx.edu.utez.rbbackendcomite.models.userHasEvents;

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
    private EventEntity event;

    // Relación hacia usuario
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    // Campo adicional
    private Boolean confirmed = false; // si el usuario confirmó o no

}