package mx.edu.utez.rbbackendcomite.models.user;

import mx.edu.utez.rbbackendcomite.models.group.GroupEntity;
import mx.edu.utez.rbbackendcomite.models.role.RoleEntity;


public class UserResponseDto {
    private Long id;
    private String username;
    private String fullName;
    private String phone;
    private String email;
    private GroupInfo group;
    private RoleEntity role;

    public UserResponseDto(UserEntity user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.fullName = user.getFullName();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.group = user.getGroup() != null ? new GroupInfo(user.getGroup()) : null;
    }

    public static class GroupInfo {
        private Long id;
        private String name;
        private String municipality;
        private String neighborhood;

        public GroupInfo(GroupEntity group) {
            this.id = group.getId();
            this.name = group.getName();
            this.municipality = group.getMunicipality();
            this.neighborhood = group.getNeighborhood();

        }

        // Getters
        public Long getId() {
            return id;
        }
        public String getName() {
            return name;
        }

        public String getMunicipality() {
            return municipality;
        }

        public String getNeighborhood() {
            return neighborhood;
        }
    }



    // Getters
    public Long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getFullName() {
        return fullName;
    }
    public String getPhone() {
        return phone;
    }
    public String getEmail() {
        return email;
    }

    public RoleEntity getRole() {
        return role;
    }
    public GroupInfo getGroup() {
        return group;
    }
}
