package com.example.library_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_roles")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_roles_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "library_id", nullable = false)
    private Library library;

    @Enumerated(EnumType.STRING)
    private Role role;

    private int penalties;
    // Constructors, Getters, and Setters
    public UserRole() {}

    public UserRole(User user, Library library, Role role) {
        this.user = user;
        this.library = library;
        this.role = role;
    }

    public int getId() { return id; }
    public User getUser() { return user; }
    public Library getLibrary() { return library; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    public int getPenalties() { return penalties; }
    public void addPenalty() { penalties++; }
}
