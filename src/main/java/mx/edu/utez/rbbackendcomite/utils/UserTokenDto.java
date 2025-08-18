package mx.edu.utez.rbbackendcomite.utils;


import mx.edu.utez.rbbackendcomite.models.role.RoleEntity;

public class UserTokenDto {
    private String username;
    private String fullName;
    private String phone;
    private String email;
    private RoleEntity role;

    public UserTokenDto(String username, String fullName, String phone, String email, RoleEntity role) {
        this.username = username;

        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.role = role;
    }

    public UserTokenDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }
}
