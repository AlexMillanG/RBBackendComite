package mx.edu.utez.rbbackendcomite.models.role;

import jakarta.persistence.*;
import lombok.Data;
//import mx.edu.utez.rbbackcomite.models.user.UserEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // "ADMIN", "GROUP_ADMIN", "MEMBER"

    public RoleEntity() {
    }

    public RoleEntity(Long id, String name) {
        this.id = id;
        this.name = name;
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

    /*@OneToMany(mappedBy = "role")
    private List<UserEntity> users = new ArrayList<>();*/


}