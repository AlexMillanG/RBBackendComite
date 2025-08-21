package mx.edu.utez.rbbackendcomite.models.userHasEvents;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonBackReference

    private EventEntity event;

    // Relación hacia usuario
    @ManyToOne
    @JsonBackReference

    @JoinColumn(name = "user_id")
    private UserEntity user;

    // Campo adicional
    private Boolean confirmed = false; // si el usuario confirmó o no

}