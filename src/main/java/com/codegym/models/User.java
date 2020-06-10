package com.codegym.models;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(length = 64)
    private String email;
    private String password;

    @ManyToOne
    private Role role;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
