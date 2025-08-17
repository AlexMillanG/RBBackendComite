package mx.edu.utez.rbbackendcomite.models.role;

import jakarta.persistence.*;
import mx.edu.utez.rbbackendcomite.models.user.UserEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")

public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // "ADMIN", "GROUP_ADMIN", "MEMBER"

    @OneToMany(mappedBy = "role")
    private List<UserEntity> users = new ArrayList<>();

    public RoleEntity(Long id, String name, List<UserEntity> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }

    public RoleEntity() {
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

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}