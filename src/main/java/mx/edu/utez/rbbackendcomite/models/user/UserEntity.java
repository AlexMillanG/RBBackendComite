package mx.edu.utez.rbbackendcomite.models.user;

import jakarta.persistence.*;
import lombok.Data;
import mx.edu.utez.rbbackendcomite.models.group.GroupEntity;
import mx.edu.utez.rbbackendcomite.models.role.RoleEntity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import mx.edu.utez.rbbackendcomite.models.userHasEvents.EventParticipantEntity;

@Entity
@Table(name = "users")
@Data

public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String fullName;
    private String phone;
    private String email;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @JsonIgnore
    private GroupEntity group;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventParticipantEntity> events = new ArrayList<>();


}