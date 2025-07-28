package mx.edu.utez.rbbackendcomite.models.group;

import jakarta.persistence.*;
import lombok.Data;
import mx.edu.utez.rbbackendcomite.models.event.EventEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_groups")
@Data
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String municipality;
    private String neighborhood;

   /* @OneToMany(mappedBy = "group")
    private List<UserEntity> members = new ArrayList<>();*/

    @OneToMany(mappedBy = "group")
    private List<EventEntity> events = new ArrayList<>();


}