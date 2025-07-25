package mx.edu.utez.rbbackendcomite.models.eventType;

import jakarta.persistence.*;
import lombok.Data;

//Aqui va la importacion de event type
//import mx.edu.utez.rbbackcomite.models.event.EventEntity;

@Entity
@Table(name = "event_types")
@Data
public class EventTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // "Limpieza", "Reforestación", etc.

    /*@OneToMany(mappedBy = "type")
    private List<EventEntity> events = new ArrayList<>();*/
}
