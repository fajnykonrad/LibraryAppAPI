package com.example.library_api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    private String name;
    private String mail;
    private int penalties;
    @Column(name = "is_deleted")
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "library_id")
    @JsonBackReference
    private Library library;
    // Getters and setters

    public User() {
    }
    public User(String name, String mail, Library library) {
        this.name = name;
        this.mail = mail;
        this.library = library;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getPenalties() {
        return penalties;
    }

    public void addPenalty() {
        this.penalties++;
    }

    public Library getLibrary() {
        return library;
    }
    public boolean getStatus() {
        return isDeleted;
    }

    public void setStatus(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}   