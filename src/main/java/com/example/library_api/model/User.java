package com.example.library_api.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    private String name;
    
    @Column(unique = true)
    private String mail;
    private String password;
    private boolean is_deleted;


    public User() {}

    public User(String name, String email) {
        this.name = name;
        this.mail = email;
    }
    // Getters and Setters
    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getMail() { return mail; }
    public void setMail(String email) { this.mail = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}   